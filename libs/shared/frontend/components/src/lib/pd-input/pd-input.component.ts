// button.component.ts
import {
  Component,
  Input,
  Output,
  EventEmitter,
  CUSTOM_ELEMENTS_SCHEMA,
  OnInit,
} from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormControl, ReactiveFormsModule, Validators } from '@angular/forms';

enum InputType {
  STANDARD = 'standard',
  PASSWORD = 'password',
}
@Component({
  selector: 'pd-input',
  templateUrl: './pd-input.component.html',
  styleUrls: ['./pd-input.component.scss'],
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class PdInputComponent implements OnInit {
  @Input() id?: string;
  @Input({ required: true }) label!: string; //this is the text that will be displayed
  @Input() variant: 'text' | 'textarea' = 'text'; //text or textarea?  'primary' | 'secondary' | 'danger' = 'primary'
  @Input() size: 'small' | 'medium' | 'large' = 'medium';
  @Input() mode: 'standard' | 'brand' | 'dark' = 'standard';
  @Input() textWeight?: 'normal' | 'bold' = 'normal';
  @Input() icon?: string;
  @Input() type: InputType = InputType.STANDARD;

  @Input() errorMessages: { [key: string]: string } = {};
  @Input() control: FormControl = new FormControl({
    value: undefined,
    disabled: true,
  });

  @Input() placeholder = '';
  @Input() required? = false;
  @Input() maxLength = '350'; //why string?
  @Input() loading = false;
  @Input() fullWidth = false;
  @Input() isReadOnly = false;
  @Input() disabled = false;
  @Output() iconClick = new EventEmitter<Event>();

  chartCount = 0;
  showPassword = false;

  ngOnInit() {
    if (this.variant === 'textarea' && this.control) {
      const validators = [this.control.validator, Validators.maxLength(+this.maxLength)].filter(
        (x) => x !== null && x !== undefined
      );
      this.control.setValidators(validators);
      this.control.valueChanges.subscribe((value) => {
        if (!value) {
          this.chartCount = 0;
        }
      });
    }
  }

  onClickShowPassword(): void {
    this.showPassword = !this.showPassword;
  }

  handleClick(): void {
    this.iconClick.emit();
  }

  getClassObject() {
    return {
      error: this.control && this.control.invalid && this.control.touched,
      [this.mode]: !!this.mode,
      [this.size]: !!this.size,
      [this.variant]: !!this.variant,
    };
  }

  getErrorMessages() {
    if (!this.control.errors) return null;

    const firstErrorKey = Object.keys(this.control.errors)[0];
    return this.errorMessages[firstErrorKey];
  }
  /*
  onKeyDownShowPassword(event: KeyboardEvent): void {
    this.keyDownEvent.emit(event);
  }
*/
  //change and submit?
  onChangeTextArea(event: Event) {
    const eventCasted = event.target as HTMLTextAreaElement;
    this.chartCount = eventCasted.value.length;
  }
}
