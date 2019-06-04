import { Client } from './../../model/client';
import { SalesApiService } from '../../services/_sales-api.service';
import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-leads-list',
  templateUrl: './leads-list.component.html',
  styleUrls: ['../.././busyqacrm.component.css']
})
export class LeadsListComponent implements OnInit {

  leadList: Client[];


  constructor(private salesService: SalesApiService,
              private router: Router) {
  }

  ngOnInit() {
    this.salesService.clientResult$.subscribe(data => {
      if (data != null) {
        this.leadList = data;
        console.log('Successful Loading Lead List!');
        console.log(this.leadList);
      }
    });

    this.salesService.getLeadsList();
    console.log(this.leadList);

  }

}



