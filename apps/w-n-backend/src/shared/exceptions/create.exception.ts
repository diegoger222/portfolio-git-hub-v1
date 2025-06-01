import { CustomExceptionCode } from './codes/custom.exceptions.code';

export class CreateException extends Error {
  feature: string;
  code: number;
  constructor(message: string, feature: string) {
    super(message);
    this.name = `Create${feature}Exception`;
    this.feature = feature;
    this.code = CustomExceptionCode.CREATE_ERROR_CODE;
  }
}
