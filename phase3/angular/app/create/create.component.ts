import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators} from '@angular/forms';
import {Account} from '../account';
import { AccountService } from '../accountService';

@Component({
    selector: 'app-create',
    templateUrl: './create.component.html',
    
})

export class CreateComponent implements OnInit{
      
    acc: Account = new Account("","","","",0 ,0);
    message:any;

    public onSubmit(){
        let response= this.service.createAccount(this.acc);
        response.subscribe((data) => this.message=data); 
        
    }


        createAccountForm= this.fb.group({
        name: ['', [Validators.required, Validators.maxLength(25), Validators.pattern('[a-zA-Z ]*')]],
        mobileNum: ['',[Validators.required, Validators.maxLength(10),Validators.minLength(10)]],
        email: ['',[Validators.required, Validators.email]],
        dob: ['',Validators.required],
        address: ['',Validators.required]
        });  

    constructor(private fb: FormBuilder, private service:AccountService){  
     }

    ngOnInit(){}

}