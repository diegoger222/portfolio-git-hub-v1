import { Component, Input, Output, EventEmitter, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'pd-app-button',
  templateUrl: './pd-button.component.html',
  styleUrls: ['./pd-button.component.scss'],
  standalone: true,
  imports: [CommonModule],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class ButtonComponent {
  @Input() id!: string;
  @Input() label = 'Button';
  @Input() variant: 'primary' | 'secondary' | 'danger' = 'primary';
  @Input() size: 'small' | 'medium' | 'large' = 'medium';
  @Input() textWeight?: 'normal' | 'bold' = 'normal';
  @Input() icon?: string;
  @Input() type?: 'label' | 'icon-left' | 'icon-right' | 'icon-only' = 'label';
  @Input() loading = false;
  @Input() fullWidth = false;
  @Input() disabled = false;
  @Input() submit? = false;
  @Output() clickEvent = new EventEmitter<Event>();
  @Output() keyDownEvent = new EventEmitter<KeyboardEvent>();

  get buttonClasses(): string {
    return `btn ${this.variant} ${this.size} ${this.loading ? 'loading' : ''}`;
  }

  handleClick(event: Event): void {
    this.clickEvent.emit(event);
  }

  handleKeyDown(event: KeyboardEvent): void {
    this.keyDownEvent.emit(event);
  }

  getClassObject() {
    return {
      [this.size]: !!this.size,
      [this.variant]: !!this.variant,
    };
  }

  getTextAlignment(): string {
    return this.type === 'icon-left' ? 'row' : 'row-reverse';
  }
}
