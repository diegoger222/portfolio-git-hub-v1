import { Module } from '@nestjs/common';
import { WPrismaClient } from './services/prisma.service';

@Module({
  providers: [WPrismaClient],
})
export class CoreModule {}
