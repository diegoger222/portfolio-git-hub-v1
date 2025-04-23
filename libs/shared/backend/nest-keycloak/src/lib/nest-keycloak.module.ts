import { Logger, Module } from '@nestjs/common';
import { NestKeycloakService } from './services/nest-keycloak.service';
import { APP_GUARD } from '@nestjs/core';
import { AuthGuard, ResourceGuard, RoleGuard } from 'nest-keycloak-connect';
import { NestKeycloakController } from './controller/nest-keycloak.controller';

@Module({
  controllers: [NestKeycloakController],
  providers: [
    NestKeycloakService,
    {
      provide: APP_GUARD,
      useClass: AuthGuard,
    },
    {
      provide: APP_GUARD,
      useClass: ResourceGuard,
    },
    {
      provide: APP_GUARD,
      useClass: RoleGuard,
    },
    Logger,
  ],
  exports: [NestKeycloakService],
})
export class NestKeycloakModule {}
