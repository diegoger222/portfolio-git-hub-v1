import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import {
  ButtonComponent,
  TestComponent,
} from '@portfolio-git-hub-v1/generic-components';

@NgModule({
  declarations: [],
  imports: [AppComponent, TestComponent, ButtonComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [],
})
export class AppModule {}
