import { Body, Controller, Get } from '@nestjs/common';
import { NestKeycloakService } from '../services/nest-keycloak.service';
import { LoginRequestBody } from '@portfolio-git-hub-v1/ts-interfaces';

@Controller('auth')
export class NestKeycloakController {
  constructor(private readonly keycloakService: NestKeycloakService) {}

  @Get('login')
  login(@Body() loginDetails: LoginRequestBody) {
    return this.keycloakService.login(loginDetails.email, loginDetails.password);
  }
}
