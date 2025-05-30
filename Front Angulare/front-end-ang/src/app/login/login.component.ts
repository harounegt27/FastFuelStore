import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from '../services/auth/auth.service';
import { Router } from '@angular/router';
import { UserStorageService } from '../services/stockage/user-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginForm : FormGroup;
  hidePassword = true;

  constructor(
    private fb:FormBuilder,
    private snackBar:MatSnackBar,
    private authService: AuthService,
    private router: Router)
  {}

  ngOnInit(): void{
    this.loginForm = this.fb.group({
      email : [null, [Validators.required, Validators.email]],
      password : [null, [Validators.required]],
    });
  }

  toggelPasswordVisibility(){
    this.hidePassword = !this.hidePassword;
  }

  onSubmit():void{
    const username = this.loginForm.get('email')!.value;
    const pw = this.loginForm.get('password')!.value;

    this.authService.login(username,pw).subscribe(
      (res) => {
        if(UserStorageService.isAdminLoggedIn()){
          this.router.navigateByUrl('admin/dashboard');
        }else if(UserStorageService.isClientLoggedIn()){
          this.router.navigateByUrl('client/dashboards');
        }
      },
      (error) => {
        this.snackBar.open('Echèc de la connexion','ERROR',{duration:5000});
      }
    )
  }



}
