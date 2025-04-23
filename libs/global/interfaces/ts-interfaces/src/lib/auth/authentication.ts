export interface LoginRequestBody {
  email: string;
  password: string;
}

export interface LoginResponse {
  access_token: string;
  scope: string;
  refresh_token: string;
  token_type: string;
  session_state: string;
  'not-before-policy': number;
  refresh_expires_in: number;
  expires_in: number;
}

export interface UserInfoResponse {
  sub: string;
  email_verified: boolean;
  preferred_username: string;
}
