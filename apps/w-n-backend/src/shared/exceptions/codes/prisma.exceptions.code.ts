export const prismaQueryErrorCode = {
  P2002: {
    message: (modelName: string, fieldName: string) =>
      `Prisma error: There is a unique constraint violation on the model ${modelName} with the fields: ${fieldName}`,
  },
  P2003: {
    message: (modelName: string, target: string) =>
      `Prisma error: There is a foreign key constraint failed on the model ${modelName} with the fields: ${target}`,
  },
  P2010: {
    message: () => `Prisma error: Raw query failed.`,
  },
  P2011: {
    message: (modelName: string, target: string) =>
      `Prisma error: Null constraint violation on the model ${modelName} with the fields: ${target}`,
  },
  P2025: {
    message: (modelName: string, target: string) =>
      `Prisma error: An operation failed on the model ${modelName} because it depends on one or more records that were required but not found: ${target}`,
  },
};
