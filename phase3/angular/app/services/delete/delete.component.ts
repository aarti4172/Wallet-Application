import { Component, OnInit } from '@angular/core';
import { AccountService } from 'src/app/accountService';

@Component({
    selector: 'app-delete',
    templateUrl: './delete.component.html',
    
})

export class DeleteComponent implements OnInit{


    onClick(ts){
        console.log(ts.value);
    }
    onClick1(){
        alert("Are you sure you want to delete your account?");
        
    }

    constructor(private service: AccountService){}
    ngOnInit(){
       
    }

}