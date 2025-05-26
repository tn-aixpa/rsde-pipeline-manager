import { Routes } from '@angular/router';
import { ElaborationListComponent } from './elaboration-list/elaboration-list.component';
import { ElaborationDetailComponent } from './elaboration-detail-component/elaboration-detail.component';
import { ElaborationCreateComponent } from './elaboration-create-component/elaboration-create.component';

export const routes: Routes = [
    { path: '', component: ElaborationListComponent },
    { path: 'create', component: ElaborationCreateComponent, pathMatch: 'full' },
    { path: ':id', component: ElaborationDetailComponent, pathMatch: 'full' },
];
