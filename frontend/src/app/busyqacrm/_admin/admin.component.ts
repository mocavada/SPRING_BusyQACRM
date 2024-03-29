import { User } from '../model/auth-user';
import { HttpClient } from '@angular/common/http';

import { Component, OnInit } from '@angular/core';


import { TokenStorageService } from '../security/token-storage.service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['.././busyqacrm.component.css']
})
export class AdminComponent implements OnInit {
  roles: string[];
  authority: string;
  username: string;


  board: string;
  errorMessage: string;
  users: User[];


  constructor(private http: HttpClient,
              private router: Router,
              private tokenStorage: TokenStorageService) {

              }

    ngOnInit() {

    this.roleAccess();
    console.log('url ' + this.router.url);
  }

  roleAccess() {
    if (this.tokenStorage.getToken()) {
      this.roles = this.tokenStorage.getAuthorities();

    // Find the User ROLE
      this.roles.find(role => {
        if (this.roles.includes('ROLE_ADMIN')) {
          this.authority = 'admin';
          return false;
        } else if (this.roles.includes('ROLE_AUDIT')) {
          this.authority = 'audit';
          return false;
        } else if (this.roles.includes('ROLE_SALES')) {
          this.authority = 'sales';
          return false;
        } else {
          this.authority = 'user';
          return true;
        }
      });

  //     // Find the User USERNAME
      this.username = this.tokenStorage.getUsername();
    }
  }

}
