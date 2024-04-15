import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { EventServiceService } from '../../services/event-service.service';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, Validators, AbstractControl, ValidationErrors } from '@angular/forms';
import { Router } from '@angular/router';
import { NgIf } from '@angular/common';
import { NewUser } from './newUser';
import { CustomValidators } from './CustomValidators';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, FormsModule, NgIf],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{
  loginForm!: FormGroup;
  signUpForm!: FormGroup;
  signupFormVisible: boolean = false;
  forgotPasswordFormVisible: boolean = false;

  get email(){
    return this.loginForm.get('email')!;
  }
  get password(){
    return this.loginForm.get('password')!;
  }
  get name(){
    return this.signUpForm.get('name')!;
  }
  get signUpEmail(){
    return this.signUpForm.get('signUpEmail')!;
  }
  get signUpPassword(){
    return this.signUpForm.get('signUpPassword')!;
  }
  get confirmPassword(){
    return this.signUpForm.get('confirmPassword')!;
  }


  constructor( private formBuilder: FormBuilder, private dialogRef: MatDialogRef<LoginComponent>, private eventService: EventServiceService, private router: Router){}
  ngOnInit(): void {
    this.loginForm= this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
    this.signUpForm=this.formBuilder.group({
      name:['',Validators.required],
      signUpEmail: ['',[Validators.required, Validators.email]],
      signUpPassword: ['', Validators.required],
      confirmPassword: ['', Validators.required],
    }, {validator: CustomValidators});
  }
  closeDialog(): void {
    this.dialogRef.close();
  }
  openSignupForm(): void {
    this.signupFormVisible = true;
  }

  openForgotPasswordForm(): void {
    this.forgotPasswordFormVisible = true;
  }
  
  login(): void {
    if (this.loginForm.valid) {
      const email = this.loginForm.get('email')!.value;
      const password = this.loginForm.get('password')!.value;
      this.eventService.loginUser(email, password)
        .subscribe((response)=>{
          this.dialogRef.close();
          console.log('Login is successfull', response);
          },
          (error) => {
            console.error('Error logging in:', error);
          }
        );
    } else {
      console.log('Form is invalid. Please check for errors.');
      this.loginForm.markAllAsTouched();
    }
  }
  signUp():void{
    if(this.signUpForm.valid){
      const newUser: NewUser = {
       name: this.signUpForm.get('name')?.value,
       email: this.signUpForm.get('signUpEmail')?.value,
       password: this.signUpForm.get('signUpPassword')?.value
      };
      this.eventService.signUpUser(newUser)
      .subscribe((response)=>{
        this.dialogRef.close();
          console.log('SignUp is successfull', response);
          },
          (error) => {
            console.error('Error Signing Up:', error);
          }
      );
      console.log('User Details:', newUser )
    }else {
      console.log('Form is invalid. Please check for errors.');
      this.signUpForm.markAllAsTouched();
  }

}}
