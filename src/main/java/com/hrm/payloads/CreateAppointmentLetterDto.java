package com.hrm.payloads;

import java.time.LocalDate;

import com.hrm.helper.EnumCollection.Designation;
import com.hrm.helper.EnumCollection.EmployeeStatus;
import com.hrm.helper.EnumCollection.WorkLocation;

public class CreateAppointmentLetterDto {
	private long candidateId;
	private String employeeId;
	private String name;
	private String jobTitle;
	private Designation designation;
	private WorkLocation workLocation;
	private LocalDate dateOfJoining;
	private float ctc;
	private float bondPeriod;
	private long bondBreakAmount;
	private String emailId;
	private long contactNumber;
	private EmployeeStatus employeeStatus;
	private byte[] authorisedSignature;
	private byte[] sign;
	private byte[] appointmentLetter;
	public String html = " <div style=\"margin-left:3%;\">\r\n"
			+ "            <h1 style=\"text-align:center;font-weight:bold;\"><u>APPOINTMENT LETTER</u></h1>\r\n"
			+ "           \r\n" + "     \r\n" + "\r\n" + "            <div style=\"margin-top:5%;\">\r\n"
			+ "        <p>Employee ID :<span style=\"font-weight:bold;\">" + employeeId + "</span></p>\r\n"
			+ "        <p style=\"margin-right:5%;\">Dear <span style=\"font-weight:bold;\">" + name
			+ "</span><br><br>\r\n" + "   \r\n"
			+ "          Welcome to <span style=\"font-weight:bold;\">Envision Integrated Services Private Limited</span>.<br><br>You are appointed as <span style=\"font-weight:bold;\">{{viewdata1?.designation?.replace('_',' ')  ?? \" \" | titlecase}}</span>\r\n"
			+ "          in Permanent role\r\n"
			+ "           w.e.f <span style=\"font-weight:bold;\">{{viewdata?.dateOfJoining ?? \" \" | date:'dd-MM-yyyy'}}</span> and your Base operations are from\r\n"
			+ "          {{viewdata1?.workLocation ?? \" \"}}. The following are the terms and conditions of the Employment.</p>\r\n"
			+ "   \r\n" + "        <h2 style=\"font-weight:bold;\">COMPENSATION & BENEFITS</h2>\r\n"
			+ "        <ol style=\"margin-right:5%;text-align:justify;\">\r\n" + "          <li>\r\n"
			+ "            Your total Annual Salary (CTC) will be Rs.<span style=\"font-weight:bold;\">{{viewdata1?.ctc ?? \" \" | number}}.</span> The detailed compensation\r\n"
			+ "            structure is described in Annexure- A. </li>\r\n" + "          <li>\r\n"
			+ "            Your salary will be reviewed periodically as per Company policy. However, changes in\r\n"
			+ "            your compensation are discretionary and will be based on effective performance results\r\n"
			+ "            during the period and other relevant criteria. </li>\r\n" + "          <li>\r\n"
			+ "            Permanent employees will get additional benefit and contribution to Provident Fund.\r\n"
			+ "            Computations will be on the basic component of the remuneration package and as per\r\n"
			+ "            relevant & applicable rules and regulations. </li>\r\n" + "          <li>\r\n"
			+ "            Please note that the remuneration structure may be altered/ modified at any time without\r\n"
			+ "            prior notice and your remuneration and other terms may accordingly be altered/ modified\r\n"
			+ "            from time to time. Further, all other payments/ benefits will be governed by the\r\n"
			+ "            Company's rules as well as statutory provisions in force from time to time and subject to\r\n"
			+ "            deduction of appropriate taxes at source. </li>\r\n" + "          <li>\r\n"
			+ "            Your remuneration package is strictly confidential between you and the Company and\r\n"
			+ "            should not be discussed with any one nor divulged to anyone in any manner whatsoever.\r\n"
			+ "          </li>\r\n" + "          <li>\r\n"
			+ "            Employees in India are normally paid their monthly remuneration through bank every\r\n"
			+ "            month.\r\n" + "          </li>\r\n" + "        </ol>\r\n" + "   \r\n"
			+ "        <h3 style=\"font-weight:bold;\">PERIOD OF SERVICE</h3>\r\n"
			+ "        <ol style=\"margin-right:5%;text-align:justify;\">\r\n" + "          <li>\r\n"
			+ "            Executing a <span style=\"font-weight:bold;\">{{viewdata1?.serviceCommitment ?? \"\"}}</span>-year training cum service agreement with the company.\r\n"
			+ "          </li>\r\n" + "   \r\n" + "          <li>\r\n"
			+ "            The Probation Period is six months and you will be under evaluation. During this tenure if\r\n"
			+ "            your performance is not reaching the expectation and satisfaction level, the Probation\r\n"
			+ "            period will be extended or You will be removed from the position.\r\n"
			+ "          </li>\r\n" + "        </ol>\r\n" + "   \r\n"
			+ "        <h2 style=\"font-weight:bold;\">HOURS OF WORK</h2>\r\n" + "   \r\n"
			+ "        <ol style=\"text-align:justify;margin-right:5%;\">\r\n" + "          <li>\r\n"
			+ "            Work days: 6 Days / Week.\r\n" + "          </li>\r\n" + "   \r\n" + "          <li>\r\n"
			+ "            The working hours applicable to you will be dependent upon your place of posting.\r\n"
			+ "            Further, you should be prepared to work on any shift, as may be warranted by the\r\n"
			+ "            Company's/ Client's work requirements. Depending on Organizational requirement or\r\n"
			+ "            project contingencies, your working hours may be modified/ altered from time to time.\r\n"
			+ "            You may also be required to work on holidays/ weekends depending on the nature of the\r\n"
			+ "            project's.\r\n" + "          </li>\r\n" + "          <li>\r\n"
			+ "            The Company does not make overtime payments for extra hours worked, if any.\r\n"
			+ "          </li>\r\n" + "        </ol>\r\n" + "        <!-- <div>\r\n"
			+ "          <p style=\"margin-top:30px;text-align:center;font-weight:bold;\">www.actus.com</p>\r\n"
			+ "        </div> -->\r\n" + "        <!-- <div class=\"subpage\">Page 1/2</div>\r\n"
			+ "        </div> -->\r\n" + "     \r\n" + "       \r\n" + "        <!-- <div class=\"page\"> -->\r\n"
			+ "   \r\n" + "        <h2 style=\"font-weight:bold;\">LEAVES & HOLIDAYS</h2>\r\n" + "   \r\n"
			+ "        <ol style=\"margin-right:5%;\">\r\n" + "          <li>\r\n"
			+ "            You will be entitled for 18 days paid leaves per calendar year on a prorate basis from your\r\n"
			+ "            date of joining. Earned leave will be credited to your account on a monthly basis.\r\n"
			+ "          </li>\r\n" + "          <li>\r\n"
			+ "            You will be eligible for encashment of Paid leaves as per the Basic Component of your\r\n"
			+ "            salary.\r\n" + "          </li>\r\n" + "          <li>\r\n"
			+ "            As each region may have a different set of holidays, your holiday calendar will be\r\n"
			+ "            governed by your office/ client location.\r\n" + "          </li>\r\n" + "        </ol>\r\n"
			+ "   \r\n" + "        <h2 style=\"font-weight:bold;\">UNAUTHORISED ABSENCE FROM WORK</h2>\r\n" + "   \r\n"
			+ "        <ol style=\"margin-right:3%;\">\r\n" + "          <li>\r\n"
			+ "            Unauthorized absence for a period of 3 days or more will be treated as absconding from\r\n"
			+ "            duties and shall not be entitled to any pay, allowances and proceedings during the period\r\n"
			+ "            of such absence and will lead to termination of employment.\r\n" + "          </li>\r\n"
			+ "   \r\n" + "          <li>\r\n"
			+ "            Absconding from duties with any damaging or improper returning of company's\r\n"
			+ "            belongings by the employee will be considered as stealing of company's property. So the\r\n"
			+ "            company has all due right to take legal action towards employee.\r\n" + "          </li>\r\n"
			+ "        </ol>\r\n" + "   \r\n" + "        <h2 style=\"font-weight:bold;\">RETIREMENT AGE</h2>\r\n"
			+ "   \r\n" + "        <ol>\r\n" + "          <li>\r\n"
			+ "            All employees in the company shall retire on attainment of normal retirement age of 58\r\n"
			+ "            Years fixed by the company.\r\n" + "          </li>\r\n" + "        </ol>\r\n" + "   \r\n"
			+ "        <h2 style=\"font-weight:bold;\">DISPUTES</h2>\r\n" + "   \r\n"
			+ "        <ol style=\"margin-right:5%;text-align:justify;\">\r\n" + "          <li>\r\n"
			+ "            Any disputes between yourself and the company shall be subject to the jurisdiction of and\r\n"
			+ "            be determined by a court of competent jurisdiction in Hyderabad. When you are deputed\r\n"
			+ "            overseas or assigned to work with any group company or affiliate, in the event of a\r\n"
			+ "            dispute arising due to a breach of any provision of this Agreement the company shall\r\n"
			+ "            have the right to seek relief in any court in the geographic location of your assignment\r\n"
			+ "            and the governing law shall be the applicable law of that jurisdiction.\r\n"
			+ "          </li>\r\n" + "          <li>\r\n"
			+ "            In the event of a dispute arising due to a violation of the non-compete provision of this\r\n"
			+ "            Agreement the company shall have the right to seek damages and injunctive relief in any\r\n"
			+ "            court in the geographic location of the customer(s) and the governing law shall be the\r\n"
			+ "            applicable law of that jurisdiction.\r\n" + "          </li>\r\n" + "        </ol>\r\n"
			+ "   \r\n" + "        <h2 style=\"font-weight:bold;\">BACKGROUND VERIFICATION</h2>\r\n" + "   \r\n"
			+ "        <ol style=\"margin-right:5%;text-align:justify\">\r\n" + "          <li>\r\n"
			+ "            The Company reserves the right to carry out reference verifications or background checks\r\n"
			+ "            prior to your joining the Company or during the course of your engagement with this\r\n"
			+ "            Company. Such background checks and reference verifications, amongst others, would\r\n"
			+ "            include past engagement and salary (this will include your immediate previous\r\n"
			+ "            engagement), criminal records, countries resided in or worked in, etc. The Company\r\n"
			+ "            reserves the right to carry out banned/ illegal drugs/ narcotics substance screening tests on\r\n"
			+ "            you at any point of time during your engagement. You understand and acknowledge that\r\n"
			+ "            this is a requirement and you have no objections whatsoever if such checks and\r\n"
			+ "            verifications are carried out by the Company or a third party agency engaged by the\r\n"
			+ "            Company.\r\n" + "          </li>\r\n" + "   \r\n" + "          <li>\r\n"
			+ "            In verification the information furnished by you in your application is misstated or\r\n"
			+ "            documents submitted by you are not correct or banned/ illegal drugs/ narcotics substance\r\n"
			+ "            screening test results are positive, the Company shall, at its sole discretion be entitled to\r\n"
			+ "            forthwith terminate and/ or revoke your engagement with the Company, without further\r\n"
			+ "            reference in the matter. Further, termination under this Clause will not confer on you\r\n"
			+ "            any right to stake claim of any kind of compensation from the Company.\r\n"
			+ "          </li>\r\n" + "        </ol>\r\n" + "        <!-- <div>\r\n"
			+ "          <p style=\"margin-top:30px;text-align:center;font-weight:bold;\">www.actus.com</p>\r\n"
			+ "        </div> -->\r\n" + "        <!-- </div> -->\r\n" + "   \r\n"
			+ "        <!-- <div class=\"page\"> -->\r\n"
			+ "        <h2 style=\"font-weight:bold;\">TERMINATION OF EMPLOYMENT</h2>\r\n" + "   \r\n"
			+ "        <ol style=\"margin-right:5%;text-align:justify\">\r\n"
			+ "          <li style=\"text-align:justify;\">\r\n" + "            Exit by Separation:<br>\r\n"
			+ "            <ol style=\"text-align:justify;margin-left:-12px;\" type=\"A\">\r\n"
			+ "              <li>\r\n" + "            Employee should serve 3 months notice period.<br>\r\n"
			+ "            </li>\r\n" + "            <li>\r\n"
			+ "            Employees have to pay Gross Salary in lieu of Notice period.<br>\r\n"
			+ "            </li>\r\n" + "            <li>\r\n"
			+ "            In the event that you decide to leave the company before a period of service\r\n"
			+ "              agreement you must pay an amount of {{servicebreakamount}} (INR) in lieu of the\r\n"
			+ "              breaking of service agreement.<br>\r\n" + "              </li>\r\n"
			+ "              <li>\r\n"
			+ "            The service agreement/ notice period calculated on a prorate basis from your\r\n"
			+ "            date of joining/ resignation vice-versa.\r\n" + "            </li>\r\n"
			+ "            </ol>\r\n" + "          </li>\r\n" + "   \r\n" + "          <li>\r\n"
			+ "            However should you sign any service agreement with company as a part of your\r\n"
			+ "            employment process or later in the course of your employment in the company, you will\r\n"
			+ "            then not be entitled to terminate your employment with the company unless you comply\r\n"
			+ "            with the terms and conditions of the agreement in addition to the above.\r\n"
			+ "          </li>\r\n" + "   \r\n" + "          <li>\r\n"
			+ "            Any employee leaving the organization before completion of Service agreement from his/\r\n"
			+ "            her Date of joining will be liable for recovery of any kind of payments made to him at the\r\n"
			+ "            time of joining. (Joining Bonus, Notice Period Payment, Relocation Expenses and any\r\n"
			+ "            other payment made to him at the time of his joining.)\r\n" + "          </li>\r\n"
			+ "   \r\n" + "          <li style=\"text-align:justify;\">\r\n"
			+ "            The company shall have the right to terminate this agreement forthwith, without any\r\n"
			+ "            notice and without any salary in lieu of notice period in the event of any of the following:<br>\r\n"
			+ "            <ol style=\"text-align:justify;margin-left:-12px;\" type=\"A\">\r\n"
			+ "              <li>\r\n"
			+ "            a.Unauthorized absence for a period of 3 days or more will be treated as absconding\r\n"
			+ "              from duties.<br>\r\n" + "              </li>\r\n" + "              <li>\r\n"
			+ "            b.Breach on your part of any terms and conditions of this contract and any other\r\n"
			+ "            rules made applicable to you in respect of your employment with us.<br>\r\n"
			+ "            </li>\r\n" + "            <li>\r\n"
			+ "            c.Violation on your part of the company’s rule with regards to the authenticity and\r\n"
			+ "            information declared at the time of joining the company.<br>\r\n" + "            </li>\r\n"
			+ "            <li>\r\n"
			+ "            d.Any misconduct or failure to carry out any of your duties, confidential data and\r\n"
			+ "            obligations.<br>\r\n" + "            </li>\r\n" + "            <li>\r\n"
			+ "            e.Reducing of workforce in case of cancelation of the project or Natural calamities,\r\n"
			+ "            company has due rights to terminate the employee with one month or less than\r\n"
			+ "            one month of written notice.\r\n" + "            </li>\r\n" + "            </ol>\r\n"
			+ "          </li>\r\n" + "   \r\n" + "        </ol>\r\n" + "   \r\n"
			+ "        <h2 style=\"font-weight:bold;\">GENERAL TERMS AND CONDITIONS</h2>\r\n" + "   \r\n"
			+ "        <ol style=\"margin-right:5%;text-align:justify\">\r\n" + "          <li>\r\n"
			+ "            You can be transferred to any other location, department, function, establishment, or\r\n"
			+ "            branch of the Company or subsidiary, associate or affiliate Company in case if the\r\n"
			+ "            Company requires so for business purposes. In such case you will be governed by the\r\n"
			+ "            terms and conditions of service applicable to the new assignment.\r\n"
			+ "          </li>\r\n" + "          <li>\r\n"
			+ "            You shall be bound by all the policies, rules, regulations and procedures currently\r\n"
			+ "            prevailing or that may be established by the Company in future, and any modifications\r\n"
			+ "            thereof or additions thereto, as may be declared by the Company from time to time.\r\n"
			+ "          </li>\r\n" + "          <li>\r\n"
			+ "            During your engagement with the Company, you will, at all times, observe secrecy in\r\n"
			+ "            respect of any technical, trade or business data, customers' names/ business details or any\r\n"
			+ "            other information that might come to your knowledge or possession, which according to\r\n"
			+ "            the Company are necessarily confidential and form valuable property of the Company.\r\n"
			+ "            You shall not disclose nor cause the disclosure of any such data in any manner\r\n"
			+ "            whatsoever.\r\n" + "          </li>\r\n" + "          <li>\r\n"
			+ "            In the event of your leaving the engagement with the Company, you are expected not to\r\n"
			+ "            take up employment or enter into any type of business/ commercial association with any\r\n"
			+ "            of the Company's clients or their associates, for a period of two year from the date of\r\n"
			+ "            cessation of this engagement. You have to safeguard Envision Integrated Services Pvt.\r\n"
			+ "            Ltd and its customers Intellectual Property Rights and confidential information even after\r\n"
			+ "            termination of your engagement or business relationship with Envision Integrated\r\n"
			+ "            Services Pvt. Ltd.\r\n" + "          </li>\r\n" + "          <li>\r\n"
			+ "            All software, systems, ideas, concepts, designs, documentation or any other material\r\n"
			+ "            produced by you during the period of your engagement with Envision Integrated Services\r\n"
			+ "            Pvt. Ltd will either be Intellectual Property of Envision Integrated Services Pvt. Ltd or\r\n"
			+ "            that of its Customers. You will not have any rights to such material as described above.\r\n"
			+ "          </li>\r\n" + "          <li>\r\n"
			+ "            You are required to comply with all the policies of the Company including but not limited\r\n"
			+ "            to the Code of Ethical Business Conduct, the Anti-Sexual Harassment Policy and such\r\n"
			+ "            other policies, as communicated to the associates of Envision Integrated Services Pvt. Ltd\r\n"
			+ "            from time to time. In case of any violation or failure to comply with such Company\r\n"
			+ "            Policy/ policies, the Employee shall be subjected to the disciplinary action as per company\r\n"
			+ "            policy.\r\n" + "          </li>\r\n" + "          <li>\r\n"
			+ "            Any invalid provision or any gap or uncertainty of any provision in the Appointment\r\n"
			+ "            letter that becomes apparent when performing the Appointment letter shall be replaced,\r\n"
			+ "            interpreted or Supplemented as the case may be in such a manner that the intended\r\n"
			+ "            purpose of the Appointment letter will be achieved.\r\n" + "          </li>\r\n"
			+ "          <li>\r\n"
			+ "            Should any provision of this Appointment letter be or become ineffective, or be held to be\r\n"
			+ "            invalid, this shall not affect the validity of the remaining provisions hereof. Any invalid\r\n"
			+ "            provision or any gap or uncertainty of any provision in the Appointment letter that\r\n"
			+ "            becomes apparent when performing the Appointment letter shall be replaced, interpreted\r\n"
			+ "            or Supplemented as the case may be in such a manner that the intended purpose of the\r\n"
			+ "            Appointment letter will be achieved.\r\n" + "          </li>\r\n" + "          <li>\r\n"
			+ "            From the date of last working day full & final settlement & respective documents will be\r\n"
			+ "            cleared on/before 45 days.\r\n" + "          </li>\r\n" + "          <li>\r\n"
			+ "            Under Income Tax Act, it is obligatory for all the employees to submit their Permanent\r\n"
			+ "            Account Number (PAN) to company at the time of joining for the purpose of TDS (Tax\r\n"
			+ "            Deduction at Source). In case if the employee fails to provide the PAN card details,\r\n"
			+ "            employer will not provide the FORM16 to the employee.\r\n" + "          </li>\r\n"
			+ "          <li>\r\n"
			+ "            You are to devote your full time, attention and ability to the interest of the company.\r\n"
			+ "          </li>\r\n" + "          <li>\r\n"
			+ "            By signing a copy of this letter, you are confirming that familiar with Envision Integrated\r\n"
			+ "            Services Pvt. Ltd's policies. Envision Integrated Services Pvt. Ltd. reserves the right to\r\n"
			+ "            interpret, change, suspend or terminate any of its benefits, policy plans or programs in\r\n"
			+ "            accordance with its needs from time to time.\r\n" + "          </li>\r\n"
			+ "        </ol>\r\n"
			+ "        <p>We take this opportunity to welcome you to the Envision IS family and wish you a satisfying\r\n"
			+ "          engagement with us.</p>\r\n" + "   \r\n"
			+ "          <h2 style=\"font-weight:bold;\">ACCEPTANCE OF JOINING</h2>\r\n"
			+ "          <p>The terms and conditions of this Appointment Letter are fully acceptable to me. I shall report\r\n"
			+ "            for duties on <span style=\"font-weight:bold;\">{{viewdata1?.dateOfJoining ?? \" \" | date:'dd-MM-yyyy'}}.</span></p>\r\n"
			+ "   \r\n" + "            <p>Sincerely,</p>\r\n"
			+ "            <p>For  <span style=\"font-weight:bold;\">Envision Integrated Services Private Limited</span>.</p>\r\n"
			+ "\r\n" + "            <div style=\"display:flex;\">\r\n" + "              <div >\r\n"
			+ "                <img src=\"{{'data:image/jpg;base64,' + viewdata1?.authorisedSignature}}\" style=\"height:100px;width:150px;margin-top:20px;\" />\r\n"
			+ "                <p style=\"margin-top:10px;\">Authorized Signature</p>\r\n" + "               \r\n"
			+ "                 \r\n" + "                </div>\r\n" + "             \r\n" + "               \r\n"
			+ "                  <div style=\"margin-left:600px;\">\r\n"
			+ "                    <p style=\"\">Employee Name: <span style=\"font-weight:bold;font-size:0.83vw;\">{{viewdata1?.name ?? \"\" | titlecase}}</span></p>\r\n"
			+ "                   \r\n"
			+ "                    <img src=\"{{'data:image/jpg;base64,' + viewdata?.sign}}\" style=\"height:100px;width:150px;margin-top:5px;\" />\r\n"
			+ "                      <p style=\"margin-top:3px;\">Employee Signature </p>\r\n"
			+ "                   \r\n" + "                     \r\n" + "                  </div>\r\n"
			+ "           \r\n" + "            </div>\r\n" + "</div>\r\n" + "           \r\n"
			+ "          <!-- <div>\r\n"
			+ "            <p style=\"margin-top:30px;text-align:center;font-weight:bold;\">www.actus.com</p>\r\n"
			+ "          </div> -->\r\n" + "          <!-- </div> -->\r\n"
			+ "          <!-- <div class=\"page\"> -->\r\n"
			+ "            <h2 style=\"text-align:center;\">ANNEXURE – A</h2>\r\n" + "       \r\n"
			+ "          <table class=\"ctcbreakup\" style=\"width:50%\">\r\n" + "          <tr class=\"breakup\">\r\n"
			+ "            <td class=\"salary\" >EMPLOYEE NAME</td>\r\n"
			+ "            <td class=\"salary\" colspan=\"2\">{{viewdata1?.name ?? \" \" | titlecase}}</td>\r\n"
			+ "            </tr>\r\n" + "            <tr class=\"breakup\">\r\n"
			+ "            <td class=\"salary\" >EMPLOYEE ID</td>\r\n"
			+ "            <td class=\"salary\" colspan=\"2\">{{viewdata1?.employeeId ?? \" \"}}</td>\r\n"
			+ "            </tr>\r\n" + "            <tr class=\"breakup\">\r\n"
			+ "            <td class=\"salary\" >DESIGNATION</td>\r\n"
			+ "            <td class=\"salary\" colspan=\"2\">{{viewdata1?.designation.replace('_',' ') ?? \" \" | titlecase}}</td>\r\n"
			+ "            </tr>\r\n" + "            <tr class=\"breakup\">\r\n"
			+ "            <td class=\"salary\" >DATE OF JOINING</td>\r\n"
			+ "            <td class=\"salary\" colspan=\"2\">{{viewdata1?.dateOfJoining ?? \" \" | date:'dd-MM-yyyy'}}</td>\r\n"
			+ "            </tr>\r\n" + "            <tr class=\"breakup\">\r\n"
			+ "            <td class=\"salary\" >DATE OF RELEASING</td>\r\n"
			+ "            <td class=\"salary\" colspan=\"2\">{{viewdata1?.dateOfJoining ?? \" \" | date:'dd-MM-yyyy'}}</td>\r\n"
			+ "            </tr>\r\n" + "            <tr>\r\n"
			+ "            <td class=\"salary\" colspan=\"3\" style=\"text-align:center;font-size:17px;\">EARNINGS - A</td>\r\n"
			+ "            </tr>\r\n" + "          <tr style=\"background-color: #dddddd;\">\r\n"
			+ "            <th class=\"salary\">PARTICULARS</th>\r\n"
			+ "            <th class=\"salary\">Annual</th>\r\n" + "            <th class=\"salary\">Monthly</th>\r\n"
			+ "          </tr>\r\n" + "          <tr>\r\n" + "            <td class=\"salary\">BASIC</td>\r\n"
			+ "            <td class=\"salary\">{{basicSalary | number : '1.2-2'}}</td>\r\n"
			+ "            <td class=\"salary\">{{mobasicSalary | number : '1.2-2'}}</td>\r\n" + "          </tr>\r\n"
			+ "          <tr>\r\n" + "            <td class=\"salary\">HOUSE RENT ALLOWANCE</td>\r\n"
			+ "            <td class=\"salary\">{{houseRent | number : '1.2-2'}}</td>\r\n"
			+ "            <td class=\"salary\">{{mohouseRent | number : '1.2-2'}}</td>\r\n" + "          </tr>\r\n"
			+ "          <!-- <tr>\r\n" + "            <td>CONVEYANCE</td>\r\n"
			+ "            <td>____________</td>\r\n" + "            <td>____________</td>\r\n"
			+ "          </tr> -->\r\n" + "          <tr>\r\n"
			+ "            <td class=\"salary\">SPECIAL ALLOWANCE</td>\r\n"
			+ "            <td class=\"salary\">{{specialAllowence | number : '1.2-2'}}</td>\r\n"
			+ "            <td class=\"salary\">{{mospecialAllowence | number : '1.2-2'}}</td>\r\n"
			+ "          </tr>\r\n" + "          <tr>\r\n" + "            <td class=\"salary\">MEDICAL</td>\r\n"
			+ "            <td class=\"salary\">{{medicalAllowence | number : '1.2-2'}}</td>\r\n"
			+ "            <td class=\"salary\">{{momedicalAllowence | number : '1.2-2'}}</td>\r\n"
			+ "          </tr>\r\n" + "          <tr>\r\n" + "            <td class=\"salary\">TELEPHONE</td>\r\n"
			+ "            <td class=\"salary\">{{telephone | number : '1.2-2'}}</td>\r\n"
			+ "            <td class=\"salary\">{{motelephone | number : '1.2-2'}}</td>\r\n" + "            </tr>\r\n"
			+ "            <tr>\r\n" + "            <td class=\"salary\">LEAVE TRAVEL ALLOWANCE</td>\r\n"
			+ "            <td class=\"salary\">{{leaveTravel | number : '1.2-2'}}</td>\r\n"
			+ "            <td class=\"salary\">{{moleaveTravel | number : '1.2-2'}}</td>\r\n" + "            </tr>\r\n"
			+ "            <!-- <tr>\r\n" + "            <td>SHIFT ALLOWANCES</td>\r\n"
			+ "            <td>______________</td>\r\n" + "            <td>______________</td>\r\n"
			+ "            </tr> -->\r\n" + "            <tr style=\"background-color: #dddddd;\">\r\n"
			+ "            <td class=\"salary\">GROSS SALARY</td>\r\n"
			+ "            <td class=\"salary\">{{grossSalary | number : '1.2-2'}}</td>\r\n"
			+ "            <td class=\"salary\">{{mogrossSalary | number : '1.2-2'}}</td>\r\n" + "            </tr>\r\n"
			+ "            <tr>\r\n" + "            <td class=\"salary\">EMPLOYEE PF</td>\r\n"
			+ "            <td class=\"salary\">{{EmployeePf | number : '1.2-2'}}</td>\r\n"
			+ "            <td class=\"salary\">{{moEmployeePf | number : '1.2-2'}}</td>\r\n" + "            </tr>\r\n"
			+ "            <tr>\r\n" + "            <td class=\"salary\">GRATUITY</td>\r\n"
			+ "            <td class=\"salary\">{{gratuity | number : '1.2-2'}}</td>\r\n"
			+ "            <td class=\"salary\">{{mogratuity | number : '1.2-2'}}</td>\r\n" + "            </tr>\r\n"
			+ "            <tr>\r\n" + "            <td class=\"salary\">PROFESSIONAL TAX</td>\r\n"
			+ "            <td class=\"salary\">{{professionalTax | number : '1.2-2'}}</td>\r\n"
			+ "            <td class=\"salary\">{{moprofessionalTax | number : '1.2-2'}}</td>\r\n"
			+ "            </tr>\r\n" + "            <tr style=\"background-color: #dddddd;\">\r\n"
			+ "            <td class=\"salary\">NET TAKE HOME </td>\r\n"
			+ "            <td class=\"salary\">{{netTakeHome | number : '1.2-2'}}</td>\r\n"
			+ "            <td class=\"salary\">{{monetTakeHome | number : '1.2-2'}}</td>\r\n" + "            </tr>\r\n"
			+ "            <tr>\r\n" + "            <td class=\"salary\">EMPLOYER PF</td>\r\n"
			+ "            <td class=\"salary\">{{employerPf | number : '1.2-2'}}</td>\r\n"
			+ "            <td class=\"salary\">{{moemployerPf | number : '1.2-2'}}</td>\r\n" + "            </tr>\r\n"
			+ "            <tr>\r\n" + "            <td class=\"salary\">GROUP INSURANCE</td>\r\n"
			+ "            <td class=\"salary\">{{groupInsurance | number : '1.2-2'}}</td>\r\n"
			+ "            <td class=\"salary\">{{mogroupInsurance | number : '1.2-2'}}</td>\r\n"
			+ "            </tr>\r\n" + "            <!-- <tr>\r\n" + "            <td>PERFORMACE LINKED\r\n"
			+ "              PAY(PLP)</td>\r\n" + "            <td>______________</td>\r\n"
			+ "            <td>______________</td>\r\n" + "            </tr> -->\r\n" + "            <tr>\r\n"
			+ "   \r\n" + "            </tr>\r\n" + "            <tr style=\"background-color: #dddddd;\">\r\n"
			+ "            <td class=\"salary\">TOTAL COST TO COMPANY</td>\r\n"
			+ "            <td class=\"salary\">{{totalCoastToCompany | number : '1.2-2'}}</td>\r\n"
			+ "            <td class=\"salary\">{{mototalCoastToCompany | number : '1.2-2'}}</td>\r\n"
			+ "            </tr>\r\n" + "            <tr class=\"breakup\">\r\n"
			+ "            <td class=\"salary\" colspan=\"3\" style=\"text-align:center;\">{{totalcost  | uppercase}}</td>\r\n"
			+ "            </tr>\r\n" + "          </table>\r\n" + "          <br>\r\n"
			+ "          <p>The terms and conditions of this Appointment Letter are fully acceptable to me. I shall report\r\n"
			+ "          for duties on <span style=\"font-weight:bold;\">{{viewdata?.dateOfJoining ?? \"\" | date:'dd-MM-yyyy'}}</span>.</p>\r\n"
			+ "   \r\n" + "          <p>Sincerely,</p>\r\n"
			+ "          <p>For <span style=\"font-weight:bold;\">Envision Integrated Services Private Limited</span>.\r\n"
			+ "\r\n" + "            <div style=\"display:flex;\">\r\n" + "            <div >\r\n"
			+ "              <img src=\"{{'data:image/jpg;base64,' + viewdata1?.authorisedSignature}}\" style=\"height:100px;width:150px;margin-top:20px;\" />\r\n"
			+ "              <p style=\"margin-top:10px;\">Authorized Signature</p>\r\n" + "             \r\n"
			+ "               \r\n" + "              </div>\r\n" + "           \r\n" + "             \r\n"
			+ "                <div style=\"margin-left:600px;\">\r\n"
			+ "                  <p style=\"\">Employee Name: <span style=\"font-weight:bold;font-size:0.83vw;\">{{viewdata1?.name ?? \"\" | titlecase}}</span></p>\r\n"
			+ "                 \r\n"
			+ "                  <img src=\"{{'data:image/jpg;base64,' + viewdata?.sign}}\" style=\"height:100px;width:150px;margin-top:5px;\" />\r\n"
			+ "                    <p style=\"margin-top:3px;\">Employee Signature </p>\r\n" + "                 \r\n"
			+ "                   \r\n" + "                </div>\r\n" + "         \r\n" + "          </div>\r\n"
			+ "        </div>";

	public long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(long candidateId) {
		this.candidateId = candidateId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public WorkLocation getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(WorkLocation workLocation) {
		this.workLocation = workLocation;
	}

	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public float getCtc() {
		return ctc;
	}

	public void setCtc(float ctc) {
		this.ctc = ctc;
	}

	public float getBondPeriod() {
		return bondPeriod;
	}

	public void setBondPeriod(float bondPeriod) {
		this.bondPeriod = bondPeriod;
	}

	public long getBondBreakAmount() {
		return bondBreakAmount;
	}

	public void setBondBreakAmount(long bondBreakAmount) {
		this.bondBreakAmount = bondBreakAmount;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public EmployeeStatus getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(EmployeeStatus employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	public byte[] getAuthorisedSignature() {
		return authorisedSignature;
	}

	public void setAuthorisedSignature(byte[] authorisedSignature) {
		this.authorisedSignature = authorisedSignature;
	}

	public byte[] getSign() {
		return sign;
	}

	public void setSign(byte[] sign) {
		this.sign = sign;
	}

	public byte[] getAppointmentLetter() {
		return appointmentLetter;
	}

	public void setAppointmentLetter(byte[] appointmentLetter) {
		this.appointmentLetter = appointmentLetter;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

}
