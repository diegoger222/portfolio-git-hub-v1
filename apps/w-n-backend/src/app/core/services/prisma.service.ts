import { PrismaClient } from '.prisma/client-w';
import { Injectable, OnModuleInit } from '@nestjs/common';

@Injectable()
export class WPrismaClient extends PrismaClient implements OnModuleInit {
  async onModuleInit() {
    await this.$connect();
  }
}
