import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import {
  ButtonComponent,
  PdInputComponent,
  TestComponent,
} from '@portfolio-git-hub-v1/generic-components';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { FromUtils } from './shared/utils/form.utils';
import { CarouselComponent } from './shared/utils/components/carousel/carousel.component';
import { CarouselSingleComponent } from './shared/utils/components/carousel-single/carousel-single.component';

@Component({
  imports: [
    RouterModule,
    TestComponent,
    ButtonComponent,
    PdInputComponent,
    CarouselComponent,
    CarouselSingleComponent,
  ],
  providers: [FromUtils],
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent implements OnInit {
  title = 'pd-book-frontend';
  testForm!: FormGroup;

  ngOnInit(): void {
    this.testForm = new FormGroup({
      name: new FormControl('', {
        validators: [Validators.required, Validators.pattern(/^(?!\s*$).+/)],
        updateOn: 'blur',
      }),
    });
    //For disable : this.testForm.controls['name'].disable();
  }

  async onClickPatata() {
    console.log('patata');
  }

  constructor(public formUtils: FromUtils) {}
}
