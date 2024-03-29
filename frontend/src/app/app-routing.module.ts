import { LeadsListClientstatusComponent } from './busyqacrm/_sales/leads-list-clientstatus/leads-list-clientstatus.component';

import { OverviewSalesComponent } from './busyqacrm/_sales/overview-sales/overview-sales.component';
import { FinanceComponent } from './busyqacrm/_audit/finance.component';
import { StudentDetailsComponent } from './busyqacrm/_audit/student-details/student-details.component';
import { SearchStudentComponent } from './busyqacrm/_audit/search-student/search-student.component';

import { AcademicsComponent } from './busyqacrm/_sales/academics.component';


import { UsersListComponent } from './busyqacrm/_admin/users-list/users-list.component';
import { CoursescheduleListComponent } from './busyqacrm/_sales/courseschedule-list/courseschedule-list.component';
import { CourseListComponent } from './busyqacrm/_sales/course-list/course-list.component';
import { Routes, RouterModule, CanActivate } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';

import { HomeComponent } from './home/home.component';
import { AdminComponent } from './busyqacrm/_admin/admin.component';
// AUDIT
import { AuditComponent } from './busyqacrm/_audit/audit.component';
import { StudentsListComponent } from './busyqacrm/_audit/students-list/students-list.component';
// SALES
import { SalesComponent } from './busyqacrm/_sales/sales.component';
import { LeadsListComponent } from './busyqacrm/_sales/leads-list/leads-list.component';
import { SearchLeadComponent } from './busyqacrm/_sales/search-lead/search-lead.component';
import { LeadDetailsComponent } from './busyqacrm/_sales/lead-details/lead-details.component';
import { UserDetailsComponent } from './busyqacrm/_sales/user-details/user-details.component';

import { AuthGuardService } from './busyqacrm/services/auth-guard.service';
import { RegisterComponent } from './busyqacrm/public/register/register.component';
import { LoginComponent } from './busyqacrm/public/login/login.component';
import { ResetPasswordComponent } from './busyqacrm/public/reset-password/reset-password.component';




export const router: Routes = [
    { path: '', redirectTo: '/auth/login', pathMatch: 'full' },
    { path: 'home', component: HomeComponent },
    { path: 'auth', children: [
      { path: 'signup', component: RegisterComponent },
      { path: 'login', component: LoginComponent },
      { path: 'resetpassword', component: ResetPasswordComponent, canActivate: [AuthGuardService] }
    ]},

    { path: 'dashboard', children: [
      { path: 'admin', component: AdminComponent, canActivate: [AuthGuardService]},
      { path: 'admin/userlist', component: UsersListComponent},
      { path: 'audit', component: AuditComponent },
      { path: 'audit/finance', component: FinanceComponent },
      { path: 'audit/studentslist', component: SearchStudentComponent },
      { path: 'audit/student/:email', component: StudentDetailsComponent, canActivate: [AuthGuardService] },
      { path: 'sales', component: SalesComponent },
      { path: 'sales/overview', component: OverviewSalesComponent },

      { path: 'sales/academics', component: AcademicsComponent },
      { path: 'sales/leadslist', component: SearchLeadComponent },
      { path: 'sales/leadslist/:status', component: LeadsListClientstatusComponent },
      { path: 'sales/lead/:email', component: LeadDetailsComponent, canActivate: [AuthGuardService] },
      { path: 'sales/7o8ae0geOJhRKQ9s2S7o8ae0geOJhRKQ9s2S/:username', component: UserDetailsComponent },
      { path: 'sales/searchlead', component: SearchLeadComponent },
      { path: 'sales/courselist', component: CourseListComponent },
      { path: 'sales/courseschedulelist', component: CoursescheduleListComponent }

    ]}


    // { path: 'dashboard', children: [
    //   { path: '', component: BusyQaCrmComponent, canActivate: [AuthGuardService] },
    //   { path: 'leads', component: LeadsListComponent, canActivate: [AuthGuardService] },
    //   { path: 'students', component: StudentsListComponent, canActivate: [AuthGuardService] },
    //   { path: 'auth', component: UsersListComponent, canActivate: [AuthGuardService] },

    // ]}

  ];

export const routes: ModuleWithProviders = RouterModule.forRoot(router);



