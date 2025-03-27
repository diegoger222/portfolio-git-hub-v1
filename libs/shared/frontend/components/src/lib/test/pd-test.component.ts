import { Component, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  standalone: true,
  selector: 'pd-test',
  imports: [CommonModule],
  templateUrl: './pd-test.component.html',
  styleUrl: './pd-test.component.scss',
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class TestComponent {}
