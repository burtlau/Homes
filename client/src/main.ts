import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';

import { provideAuth0 } from '@auth0/auth0-angular';
import 'dotenv/config';

bootstrapApplication(AppComponent, {
  providers: [
    provideAuth0({
      domain: 'dev-sqg5nke04p3kf76r.us.auth0.com',
      clientId: 'bEUGcKgW4fYPnvmV8Bn02Dwhmsr8HdmL',
      authorizationParams: {
        redirect_uri: window.location.origin
      }
    }),
  ]
});

bootstrapApplication(AppComponent, appConfig)
  .catch((err) => console.error(err));
