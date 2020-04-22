import {Component,OnInit} from '@angular/core';

@Component({
    selector: 'app-first',
    templateUrl: './firstPage.component.html',
    styleUrls:['./firstPage.component.css']
})

export class FirstPageComponent implements OnInit{
    
    openNav(){
        document.getElementById("mySidenav").style.width="250px";
        document.getElementById("main").style.marginLeft="250px";
        document.body.style.backgroundColor="rgba(0,0,0,0.4)";
    }

    closeNav(){
        document.getElementById("mySidenav").style.width = "0";
        document.getElementById("main").style.marginLeft= "0";
        document.body.style.backgroundColor = "white";
    }

    constructor() {}
    ngOnInit(){}

}