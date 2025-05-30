import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from '../services/auth/auth.service';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  signupForm!: FormGroup;
  hidePassword = true;


  constructor( private fb:FormBuilder,
     private snackBar:MatSnackBar,
     private authService: AuthService,
     private router: Router,
    ){

  }

  ngOnInit():void{
    this.signupForm = this.fb.group({
      name: [null, [Validators.required]],
      email: [null, [Validators.required, Validators.email]],
      password: [null, [Validators.required, Validators.minLength(8)]],
      confirmPassword: [null, [Validators.required]],
    })
  }

  toggelPasswordVisibility(){
    this.hidePassword= !this.hidePassword;
  }

  onSubmit(){
    const password = this.signupForm.get('password')?.value;
    const confirmPassword = this.signupForm.get('confirmPassword')?.value;

    if(password !== confirmPassword){
      this.snackBar.open('Assurez votre confirmation de mot de passe.','Fermer',{duration :5000 ,panelClass:'error-snackbar'});
      return;
    }

    this.authService.register(this.signupForm.value).subscribe(
      (response) => {
        this.snackBar.open('Inscription réussie','Fermer',{duration:5000});
        this.router.navigateByUrl("/login");
      },
      (error) => {
        this.snackBar.open('Échec de l"inscription veuillez réessayer.','Fermer',{duration:5000,panelClass:'error-snackbar'});
      }
    )
  }
}
