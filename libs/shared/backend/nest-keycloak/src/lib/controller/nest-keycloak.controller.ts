import { Body, Controller, Post } from '@nestjs/common';
import { NestKeycloakService } from '../services/nest-keycloak.service';
import { LoginRequestBody } from '@portfolio-git-hub-v1/ts-interfaces';
import { Unprotected } from 'nest-keycloak-connect';

@Controller('auth')
export class NestKeycloakController {
  constructor(private readonly keycloakService: NestKeycloakService) {}

  @Post('login')
  @Unprotected()
  login(@Body() loginDetails: LoginRequestBody) {
    return this.keycloakService.login(loginDetails.email, loginDetails.password);
  }

  @Post('refresh-token')
  @Unprotected()
  refreshToken(@Body('refreshToken') refreshToken: string) {
    return this.keycloakService.refreshToken(refreshToken);
  }

  @Post('logout')
  logOut(@Body('refreshToken') refreshToken: string) {
    return this.keycloakService.logout(refreshToken);
  }
}
