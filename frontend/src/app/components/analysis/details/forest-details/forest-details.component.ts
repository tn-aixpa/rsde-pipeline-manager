import { Component, Input } from '@angular/core';
import { Elaboration } from '../../../../elaboration.service';
import { MapVisComponent } from '../../../map-vis/map-vis.component';

@Component({
  selector: 'app-forest-details',
  standalone: true,
  imports: [MapVisComponent],
  templateUrl: './forest-details.component.html',
  styleUrl: './forest-details.component.scss'
})
export class ForestDetailsComponent {

  @Input()
  elaboration?: Elaboration; // Define the type based on your Elaboration model, e.g., Elaboration
}
