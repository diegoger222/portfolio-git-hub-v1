import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { NxWelcomeComponent } from './nx-welcome.component';
import {
  ButtonComponent,
  PdInputComponent,
  TestComponent,
} from '@portfolio-git-hub-v1/generic-components';

@Component({
  imports: [
    NxWelcomeComponent,
    RouterModule,
    TestComponent,
    ButtonComponent,
    PdInputComponent,
  ],
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  title = 'pd-book-frontend';

  async onClickPatata() {
    console.log('patata');
  }
}
