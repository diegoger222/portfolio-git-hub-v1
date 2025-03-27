import { FormControl, FormGroup } from '@angular/forms';

export class FromUtils {
  castToFromControl(controlName: string, control: FormGroup) {
    return control.get(controlName) as FormControl;
  }
}
