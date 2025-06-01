import { CustomExceptionCode } from './codes/custom.exceptions.code';

export class DeleteException extends Error {
  feature: string;
  code: number;
  constructor(message: string, feature: string) {
    super(message);
    this.name = `Delete${feature}Exception`;
    this.feature = feature;
    this.code = CustomExceptionCode.DELETE_ERROR_CODE;
  }
}
