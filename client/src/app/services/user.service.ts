import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private user: User = { id: 1, username: 'admin', email: 'admin@example.com' };

  getCurrentUser(): Observable<User> {
    return of(this.user);
  }

  updateProfile(user: User): Observable<User> {
    this.user = user;
    return of(this.user);
  }
}
