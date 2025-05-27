import { Component, Input, OnInit } from '@angular/core';
import { Elaboration, ElaborationService } from '../../../../elaboration.service';
import { MapVisComponent } from '../../../map-vis/map-vis.component';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-forest-details',
  standalone: true,
  imports: [MapVisComponent, NgIf],
  templateUrl: './forest-details.component.html',
  styleUrl: './forest-details.component.scss'
})
export class ForestDetailsComponent implements OnInit{

  @Input()
  elaboration?: Elaboration; 

  link?: string;

  constructor(private elaborationService: ElaborationService) { }
  ngOnInit(): void {
    if (this.elaboration?.status == 'COMPLETED') {
      this.elaborationService.generatePresignedUrl(this.elaboration.id!, this.elaboration.parameters!.outputName!).subscribe(value => {
        this.link = value.url;
      });

    }
  }
}
