import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/shared/models/user.account';
import { AuthService } from 'src/app/shared/services/auth.service';
import { LoginService } from './services/login.service';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [LoginService]
})
export class LoginComponent implements OnInit {
  public user: User;
  public anio = new Date().getFullYear();
  public loginForm;

  constructor(
    private loginService: LoginService,
    private authService: AuthService,
    private router: Router,
    private formBuilder: FormBuilder,
  ) {
    this.user = new User();

    this.loginForm = this.formBuilder.group({
      username: '',
      password: ''
    });
  }

  ngOnInit(): void {}

  onSubmit (userData: any) {
    console.log(userData);
    /* this.loginService.login(this.user).subscribe(
      (res) => {
        console.log(res.body);
        const token = res.headers.get('Authorization');
        this.authService.setLoggedIn(token);
      },
      (error) => {
        console.log(error);
      },
      () => this.router.navigateByUrl('')
    ); */
  }
}
