import { Component, Input, OnInit } from '@angular/core';
import { Elaboration, ElaborationService } from '../../../../elaboration.service';
import { MapVisComponent } from '../../../map-vis/map-vis.component';
import { NgForOf, NgIf } from '@angular/common';

@Component({
  selector: 'app-forest-details',
  standalone: true,
  imports: [MapVisComponent, NgIf, NgForOf],
  templateUrl: './forest-details.component.html',
  styleUrl: './forest-details.component.scss'
})
export class ForestDetailsComponent implements OnInit{

  @Input()
  elaboration?: Elaboration; 

  links?: any[];

  constructor(private elaborationService: ElaborationService) { }
  ngOnInit(): void {
    if (this.elaboration?.status == 'COMPLETED') {
      this.elaborationService.generatePresignedUrl(this.elaboration.id!, this.elaboration.parameters!.outputName!).subscribe(value => {
        this.links = Object.keys(value).map(k => ({name: k, value: value[k]}));
      });

    }
  }
}
