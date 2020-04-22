import {Injectable} from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn:'root'
})

export class AccountService{

    constructor(private http:HttpClient){}

    public createAccount(acc){
        return this.http.post("", acc,{responseType:'text' as 'json'})      //save method of spring ka mapping address likho yahan par
    }

    public deleteAccount(accNum){
        return this.http.delete("" + accNum)

    }

    public depositMoney(){
        return this.http.post("", ,{responseType: 'text' as 'json'});
    }

    public withdrawMoney(){
        return this.http.post("",,{responseType:'text' as 'json'});

    }

    public transferMoney(){
        return this.http.post("",,{responseType:'text' as 'json'});

    }

    public showDetails(accNum){
        return this.http.get("" +accNum);

    }

    public getAllAccounts(){
        return this.http.get("");
    }

    public printTransactions(){
        return this.http.post("",,{responseType:'text' as 'json'});
    }

    public updateAccount(){
        return this.http.post("",,{responseType:'text' as 'json'});

    }



}