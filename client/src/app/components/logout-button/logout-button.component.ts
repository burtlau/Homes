import { Component, Inject } from '@angular/core';
import { AuthService } from '@auth0/auth0-angular';
import { DOCUMENT } from '@angular/common';

@Component({
  selector: 'app-logout-button',
  template: `
    <ng-container *ngIf="auth.isAuthenticated$ | async; else loggedOut">
      <button (click)="logout()">Log out</button>
    </ng-container>
    <ng-template #loggedOut>
      <button (click)="login()">Log in</button>
    </ng-template>
  `,
  styleUrls: ['./logout-button.component.css']
})
export class LogoutButtonComponent {
  constructor(@Inject(DOCUMENT) private document: Document, public auth: AuthService) {}

  logout(): void {
    this.auth.logout({ returnTo: this.document.location.origin });
  }

  login(): void {
    this.auth.loginWithRedirect();
  }
}
