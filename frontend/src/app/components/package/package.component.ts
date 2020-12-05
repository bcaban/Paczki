import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-package',
  templateUrl: './package.component.html',
  styleUrls: ['./package.component.css']
})
export class PackageComponent implements OnInit {

  packageId: string = 'not_found';

  constructor(private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    if (this.route.snapshot.paramMap.has('packageId')) {
      this.packageId = this.route.snapshot.paramMap.get('packageId');
      // TODO PAC-27: GET to backend for package
    }
  }

}
