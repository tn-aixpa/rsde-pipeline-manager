import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ElaborationDetailComponent } from './elaboration-detail.component';

describe('ElaborationDetailComponentComponent', () => {
  let component: ElaborationDetailComponent;
  let fixture: ComponentFixture<ElaborationDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ElaborationDetailComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ElaborationDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
