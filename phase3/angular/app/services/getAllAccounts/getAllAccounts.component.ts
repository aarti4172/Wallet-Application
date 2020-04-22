import { Component, OnInit } from '@angular/core';
import { AccountService } from 'src/app/accountService';

@Component({
    selector: 'app-getAll',
    templateUrl: './getAllAccounts.component.html',
    
})

export class GetAllAccountsComponent implements OnInit{

    accounts:any;

    
    constructor(private service: AccountService){}
    ngOnInit(){
        let response= this.service.getAllAccounts();
        response.subscribe((data)=> this.accounts=data) 
    }

}