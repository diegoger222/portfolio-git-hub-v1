import { CustomExceptionCode } from './codes/custom.exceptions.code';

export class UpdateException extends Error {
  feature: string;
  code: number;
  constructor(message: string, feature: string) {
    super(message);
    this.name = `Update${feature}Exception`;
    this.feature = feature;
    this.code = CustomExceptionCode.UPDATE_ERROR_CODE;
  }
}
