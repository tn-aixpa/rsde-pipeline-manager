import { DatePipe, NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import {
  ItButtonDirective,
  ItIconComponent,
  ItListComponent,
  ItListItemComponent,
  ItModalComponent,
  ItPaginationComponent,
} from 'design-angular-kit';
import {
  Elaboration,
  ElaborationService,
  PaginatedData,
} from '../elaboration.service';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-elaboration-list',
  standalone: true,
  imports: [
    ItListComponent,
    ItListItemComponent,
    ItIconComponent,
    ItPaginationComponent,
    ItButtonDirective,
    NgFor,
    NgIf,
    DatePipe,
    RouterLink,
    ItModalComponent,
  ],
  templateUrl: './elaboration-list.component.html',
  styleUrl: './elaboration-list.component.scss',
})
export class ElaborationListComponent implements OnInit {
  changerValues: Array<number> = [10, 25, 50, 100, 250];

  changerValue: number = 10;
  toDelete: Elaboration | null = null;

  protected pageData: PaginatedData<Elaboration> = {
    number: 0,
    content: [],
  };

  constructor(
    private elaborationService: ElaborationService,
    private router: Router,
  ) {}
  ngOnInit(): void {
    this.updateList();
  }

  protected updateList(): void {
    this.elaborationService
      .getElaborationList(
        this.pageData.number / this.changerValue,
        this.changerValue,
      )
      .subscribe((data: PaginatedData<Elaboration>) => {
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

  protected deleteDialog(
    event: any,
    elaboration: Elaboration,
    modal: ItModalComponent,
  ): boolean {
    event.stopPropagation();
    modal.toggle();
    this.toDelete = elaboration;
    return false;
  }

  protected doDelete(modal: ItModalComponent): void {
    if (this.toDelete) {
      this.elaborationService
        .deleteElaboration(this.toDelete.id!)
        .subscribe(() => {
          this.updateList();
          this.toDelete = null;
          modal.toggle();
        });
    }
  }
}
