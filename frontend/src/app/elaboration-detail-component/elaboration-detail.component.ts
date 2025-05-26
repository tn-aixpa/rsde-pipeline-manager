import { Component, OnInit } from '@angular/core';
import { Elaboration, ElaborationService } from '../elaboration.service';
import { ActivatedRoute } from '@angular/router';
import { DatePipe, NgIf } from '@angular/common';
import { ForestDetailsComponent } from '../components/analysis/details/forest-details/forest-details.component';
import { FloodDetailsComponent } from '../components/analysis/details/flood-details/flood-details.component';
import { LandDetailsComponent } from '../components/analysis/details/land-details/land-details.component';

@Component({
  selector: 'app-elaboration-detail',
  standalone: true,
  imports: [NgIf, DatePipe, ForestDetailsComponent, FloodDetailsComponent, LandDetailsComponent],
  templateUrl: './elaboration-detail.component.html',
  styleUrl: './elaboration-detail.component.scss'
})
export class ElaborationDetailComponent implements OnInit {

  elaboration?: Elaboration; // Define the type based on your Elaboration model

  constructor(private route: ActivatedRoute, private elaborationService: ElaborationService) { }

  ngOnInit(): void {
    // Initialization logic can go here
    let id = this.route.snapshot.paramMap.get('id');
    this.elaborationService.getElaboration(id!).subscribe(value => {
      this.elaboration = value;
    });
  }
  // Additional methods and properties can be added as needed
}
