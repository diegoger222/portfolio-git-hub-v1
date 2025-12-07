#!/bin/bash
set -e

KEYCLOAK_ADMIN=admin
KETCLOAK_ADMIN_PASSWORD=admin
# export KCADM_CONFIG=/tmp/kcadm.config
KEYCLOAK_URL="http://localhost:7080"
REALM="master"
CLIENT_ID="admin-cli"

REALM_NAME="w-backend-realm"

USER_EMAIL="test@test.test"
USER_FIRST_NAME="Test"
USER_LAST_NAME="Test"
USER_PASSWORD="test"

# Wait until Keycloak is initialized
until curl -s -o /dev/null $KEYCLOAK_URL; do
    echo "Waiting for Keycloak to be ready..."
    sleep 5
done

echo "[ ] Starting initialization of Keycloak server..."

## Log in as master realm admin
echo "Logging into Keycloak realm master as ${KEYCLOAK_ADMIN}"

TOKEN=$(curl -s -X POST "$KEYCLOAK_URL/realms/$REALM/protocol/openid-connect/token" \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "username=$KEYCLOAK_ADMIN" \
  -d "password=$KETCLOAK_ADMIN_PASSWORD" \
  -d "grant_type=password" \
  -d "client_id=$CLIENT_ID" | grep -o '"access_token":"[^"]*"' | cut -d':' -f2 | tr -d '"')

if [ -z "$TOKEN" ] || [ "$TOKEN" == "null" ]; then
  echo "❌ Error: no se pudo obtener el token de Keycloak"
  exit 1
fi

AUTH_HEADER="Authorization: Bearer $TOKEN"

# -------------------------------------------
# 🧩 1. Crear el realm si no existe
# -------------------------------------------
if ! curl -s -H "$AUTH_HEADER" "$KEYCLOAK_URL/admin/realms/$REALM_NAME" | grep -q "\"realm\":\"$REALM_NAME\""; then
  echo "🧱 Creando realm '${REALM_NAME}'..."

  curl -s -X POST "$KEYCLOAK_URL/admin/realms" \
    -H "$AUTH_HEADER" -H "Content-Type: application/json" \
    -d "{
      \"realm\": \"$REALM_NAME\",
      \"enabled\": true,
      \"registrationEmailAsUsername\": true,
      \"loginWithEmailAllowed\": true
    }"

    # -------------------------------------------
    # this is optional, configure smtp server for sending emails
    #  \"smtpServer\": {
    #    \"host\": \"$MAILHOG_HOST\",
    #    \"port\": \"1025\",
    #    \"auth\": false,
    #    \"ssl\": \"none\",
    #   \"replyTo\": \"$SMTP_RECEIVER\",
    #   \"from\": \"$SMTP_SENDER\"
    #  }
    # -------------------------------------------
    

  echo "✅ Realm '$REALM_NAME' creado."

    # -------------------------------------------
    # 👤 2. Crear usuario dentro del realm
    # -------------------------------------------
    echo "👤 Creando usuario '${USER_EMAIL}' en realm '${REALM_NAME}'..."
    curl -s -X POST "$KEYCLOAK_URL/admin/realms/$REALM_NAME/users" \
      -H "$AUTH_HEADER" -H "Content-Type: application/json" \
      -d "{
        \"username\": \"$USER_EMAIL\",
        \"enabled\": true,
        \"email\": \"$USER_EMAIL\",
        \"emailVerified\": true,
        \"firstName\": \"$USER_FIRST_NAME\",
        \"lastName\": \"$USER_LAST_NAME\"
    }"

    echo "✅ Usuario '$USER_EMAIL' creado en realm '$REALM_NAME'."

    # Obtener el ID del usuario recién creado
    USER_ID=$(curl -s -H "$AUTH_HEADER" "$KEYCLOAK_URL/admin/realms/$REALM_NAME/users?username=$USER_EMAIL" | grep -o '"id":"[^"]*"' | head -1 | cut -d'"' -f4)

    # Asignar contraseña
    echo "🔑 Configurando contraseña del usuario..."
    curl -s -X PUT "$KEYCLOAK_URL/admin/realms/$REALM_NAME/users/$USER_ID/reset-password" \
    -H "$AUTH_HEADER" -H "Content-Type: application/json" \
    -d "{
        \"type\": \"password\",
        \"value\": \"$USER_PASSWORD\",
        \"temporary\": false
    }"

    echo "✅ Usuario '$USER_EMAIL' creado y contraseña asignada."


    # -------------------------------------------
    # 🧠 3. Crear cliente (backend service)
    # -------------------------------------------
    echo "🧩 Creando cliente '${CLIENT_NAME}'..."
    curl -s -X POST "$KEYCLOAK_URL/admin/realms/$REALM_NAME/clients" \
    -H "$AUTH_HEADER" -H "Content-Type: application/json" \
    -d "{
        \"clientId\": \"$CLIENT_NAME\",
        \"secret\": \"$CLIENT_SECRET\",
        \"enabled\": true,
        \"publicClient\": false,
        \"redirectUris\": [\"*\"],
        \"serviceAccountsEnabled\": true,
        \"directAccessGrantsEnabled\": true
    }"

    echo "✅ Cliente '${CLIENT_NAME}' creado."

    # Obtener el ID del cliente
    CLIENT_ID=$(curl -s -H "$AUTH_HEADER" "$KEYCLOAK_URL/admin/realms/$REALM_NAME/clients?clientId=$CLIENT_NAME" | grep -o '"id":"[^"]*"' | head -1 | cut -d'"' -f4)

    # -------------------------------------------
    # 🦸‍♂️ 4. Asignar rol 'manage-users' al service account
    # -------------------------------------------
    echo "🦸 Asignando rol 'manage-users' al service account del cliente..."
    # Obtener ID del cliente realm-management
    REALM_MGMT_ID=$(curl -s -H "$AUTH_HEADER" "$KEYCLOAK_URL/admin/realms/$REALM_NAME/clients?clientId=realm-management" | grep -o '"id":"[^"]*"' | head -1 | cut -d'"' -f4)

    # Obtener ID del usuario service-account
    SERVICE_ACCOUNT_ID=$(curl -s -H "$AUTH_HEADER" "$KEYCLOAK_URL/admin/realms/$REALM_NAME/users?username=service-account-$CLIENT_NAME" | grep -o '"id":"[^"]*"' | head -1 | cut -d'"' -f4)

    # Obtener objeto del rol manage-users
    ROLE_JSON=$(curl -s -H "$AUTH_HEADER" "$KEYCLOAK_URL/admin/realms/$REALM_NAME/clients/$REALM_MGMT_ID/roles/manage-users")

    # Asignar rol al service account
    curl -s -X POST "$KEYCLOAK_URL/admin/realms/$REALM_NAME/users/$SERVICE_ACCOUNT_ID/role-mappings/clients/$REALM_MGMT_ID" \
      -H "$AUTH_HEADER" -H "Content-Type: application/json" \
      -d "[$ROLE_JSON]"

    echo "✅ Rol 'manage-users' asignado a '$CLIENT_NAME'."

    echo "[✓] Configuración de Keycloak completada 🎉"
else
  echo "ℹ️ Realm '$REALM_NAME' ya existe, omitiendo creación."
fi
