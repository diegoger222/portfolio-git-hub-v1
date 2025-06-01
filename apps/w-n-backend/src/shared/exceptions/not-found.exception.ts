import { CustomExceptionCode } from './codes/custom.exceptions.code';

export class NotFoundException extends Error {
  feature: string;
  code: number;
  constructor(message: string, feature: string) {
    super(message);
    this.name = `${feature}NotFoundException`;
    this.feature = feature;
    this.code = CustomExceptionCode.NOT_FOUND_ERROR_CODE;
  }
}
