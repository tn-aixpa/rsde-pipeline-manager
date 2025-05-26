import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ElaborationCreateComponent } from './elaboration-create.component';

describe('ElaborationCreateComponentComponent', () => {
  let component: ElaborationCreateComponent;
  let fixture: ComponentFixture<ElaborationCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ElaborationCreateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ElaborationCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
