import { CustomExceptionCode } from './codes/custom.exceptions.code';

export class PageNotFoundException extends Error {
  code: number;
  constructor(pageNumber: number) {
    super(`Page ${pageNumber} not found`);
    this.name = `PageNotFoundException`;
    this.code = CustomExceptionCode.PAGE_NOT_FOUND_ERROR_CODE;
  }
}
