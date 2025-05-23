import { DatePipe, NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ItButtonDirective, ItIconComponent, ItListComponent, ItListItemComponent, ItPaginationComponent } from 'design-angular-kit';
import { Elaboration, ElaborationService, PaginatedData } from '../elaboration.service';


@Component({
  selector: 'app-elaboration-list',
  standalone: true,
  imports: [ItListComponent, ItListItemComponent, ItIconComponent, ItPaginationComponent, ItButtonDirective, NgFor, NgIf, DatePipe],
  templateUrl: './elaboration-list.component.html',
  styleUrl: './elaboration-list.component.scss',
})
export class ElaborationListComponent implements OnInit{

  changerValues: Array<number> = [10, 25, 50, 100, 250];
  
  changerValue: number = 10;


  protected pageData: PaginatedData<Elaboration> = {
    number: 0,
    content: []
  };

  constructor(public elaborationService: ElaborationService) { }
  ngOnInit(): void {
    this.updateList();
  }

  protected updateList(): void {
    this.elaborationService.getElaborationList(this.pageData.number / this.changerValue, this.changerValue).subscribe((data: PaginatedData<Elaboration>) => {
      this.pageData = data;
    });
  }
  protected onPageChange(page: any): void {
    this.pageData.number = page * this.changerValue;
    this.updateList();
  }
  protected onChangerEvent(value: number): void {
    this.changerValue = value;
    this.pageData.number = 0;
    this.updateList();
  }
}
