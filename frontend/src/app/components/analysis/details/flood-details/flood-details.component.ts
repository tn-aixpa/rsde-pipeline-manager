import { Component, Input } from '@angular/core';
import { Elaboration, ElaborationService } from '../../../../elaboration.service';
import { DatePipe, NgIf } from '@angular/common';
import { MapVisComponent } from '../../../map-vis/map-vis.component';

@Component({
  selector: 'app-flood-details',
  standalone: true,
  imports: [NgIf, MapVisComponent, DatePipe],
  templateUrl: './flood-details.component.html',
  styleUrl: './flood-details.component.scss'
})
export class FloodDetailsComponent {

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
