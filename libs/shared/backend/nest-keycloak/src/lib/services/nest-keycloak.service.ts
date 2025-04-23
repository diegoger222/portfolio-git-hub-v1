import { Injectable, Logger } from '@nestjs/common';
import { ConfigService } from '@nestjs/config';
import { HttpService } from '@nestjs/axios';
import { firstValueFrom } from 'rxjs';
import { LoginResponse, UserInfoResponse } from '@portfolio-git-hub-v1/ts-interfaces';

@Injectable()
export class NestKeycloakService {
  private readonly baseURL: string;
  private readonly realm: string;
  private readonly clientId: string;
  private readonly clientSecret: string;

  constructor(
    private readonly configService: ConfigService,
    private readonly httpService: HttpService,
    private readonly logger: Logger
  ) {
    this.baseURL = this.configService.get('KEYCLOAK_BASE_URL') ?? '';
    this.realm = this.configService.get('KEYCLOAK_REALM') ?? '';
    this.clientId = this.configService.get('KEYCLOAK_CLIENT_ID') ?? '';
    this.clientSecret = this.configService.get('KEYCLOAK_CLIENT_SECRET') ?? '';
  }

  async login(email: string, password: string): Promise<LoginResponse> {
    const { data } = await firstValueFrom(
      this.httpService.post(
        `${this.baseURL}/auth/realms/${this.realm}/protocol/openid-connect/token`,
        new URLSearchParams({
          client_id: this.clientId,
          client_secret: this.clientSecret,
          grant_type: 'password',
          email,
          password,
        })
      )
    );

    return data;
  }

  async refreshToken(refreshToken: string): Promise<LoginResponse> {
    const { data } = await firstValueFrom(
      this.httpService.post(
        `${this.baseURL}/auth/realms/${this.realm}/protocol/openid-connect/token`,
        new URLSearchParams({
          client_id: this.clientId,
          client_secret: this.clientSecret,
          grant_type: 'refresh_token',
          refresh_token: refreshToken,
        })
      )
    );

    return data;
  }

  async logout(refreshToken: string) {
    await firstValueFrom(
      this.httpService.post(
        `${this.baseURL}/auth/realms/${this.realm}/protocol/openid-connect/logout`,
        new URLSearchParams({
          client_id: this.clientId,
          client_secret: this.clientSecret,
          refresh_token: refreshToken,
        })
      )
    );
  }

  async getUserInfo(accessToken: string): Promise<UserInfoResponse> {
    const { data } = await firstValueFrom(
      this.httpService.get(
        `${this.baseURL}/auth/realms/${this.realm}/protocol/openid-connect/userinfo`,
        {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        }
      )
    );

    return data;
  }
}
