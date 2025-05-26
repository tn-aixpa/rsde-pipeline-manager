import { Component, Input } from '@angular/core';
import { Elaboration } from '../../../../elaboration.service';

@Component({
  selector: 'app-land-details',
  standalone: true,
  imports: [],
  templateUrl: './land-details.component.html',
  styleUrl: './land-details.component.scss'
})
export class LandDetailsComponent {

    @Input()
    elaboration?: Elaboration; // Define the type based on your Elaboration model, e.g., Elaboration
  
}
