package com.sramanopasaka.sipanionline.sadhumargi.listener;

import com.google.gson.JsonObject;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.AddScocialRoleResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.DeleteSocialRoleResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.FamiliesResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.FamilyMembersResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.NativeFamilyResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.PasswordChangeResponse;
import com.sramanopasaka.sipanionline.sadhumargi.PasswordRecoverResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.AchievementListResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.AddAchievementResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.AddAddressResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.AddBusinessResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.AddEducationResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.AddExamResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.AddressListResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.BasicDetailsResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.BusinessListResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.DeleteAchievementResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.DeleteAddressResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.DeleteBusinessResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.DeleteEducationResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.DeleteExamResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.DharmikDetailsResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.EducationListResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.LoginResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.RegisterResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.SanghDetailsResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.UpdateBasicDetailsResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.UpdateHobbiesResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.UpdateKnowledgeResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.UpdatePromiseResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.UpdateServiceResponse;
import com.sramanopasaka.sipanionline.sadhumargi.model.City;
import com.sramanopasaka.sipanionline.sadhumargi.model.Country;
import com.sramanopasaka.sipanionline.sadhumargi.model.LoginModel;
import com.sramanopasaka.sipanionline.sadhumargi.model.State;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Name    :   pranavjdev
 * Date   : 8/9/17
 * Email : pranavjaydev@gmail.com
 */

public interface EndPointApi {

    @POST("members/auth")
    Call<LoginResponse> doLogin(@Body JsonObject profiledata);

    @POST("members/create")
    Call<LoginModel> doRegister(@Body JsonObject profiledata);

    @FormUrlEncoded
    @POST("app/basics")
    Call<BasicDetailsResponse> getBasicDetails(@Field("member_id") String memberId, @Field("app_token") String appTokewn);

    @FormUrlEncoded
    @POST("app/addresses")
    Call<AddressListResponse> getAddressList(@Field("member_id") String memberId, @Field("app_token") String appToken);

    @FormUrlEncoded
    @POST("app/achievements")
    Call<AchievementListResponse> getAchievementList(@Field("member_id") String memberId, @Field("app_token") String appToken);


    @FormUrlEncoded
    @POST("app/businesses")
    Call<BusinessListResponse> getBusiness(@Field("member_id") String memberId, @Field("app_token") String appToken);

    @FormUrlEncoded
    @POST("app/educations")
    Call<EducationListResponse> getEducation(@Field("member_id") String memberId, @Field("app_token") String appToken);




    @FormUrlEncoded
    @POST("app/dharmik")
    Call<DharmikDetailsResponse> getDharmikDetails(@Field("member_id") String memberId, @Field("app_token") String appToken);

    @FormUrlEncoded
    @POST("app/sangh")
    Call<SanghDetailsResponse> getSanghDetails(@Field("member_id") String memberId, @Field("app_token") String appToken);


    @FormUrlEncoded
    @POST("app/basics")
    Call<UpdateBasicDetailsResponse> updateBasicDetails(@Field("member_id") String memberId, @Field("app_token") String appToken,
                                                        @Field("method") String method, @Field("salution") String salution,
                                                        @Field("first_name") String first_name, @Field("middle_name") String middle_name,
                                                        @Field("last_name") String last_name, @Field("guardian_type") String guardian_type,
                                                        @Field("guardian_name") String guardian_name, @Field("mother_name") String mother_name,
                                                        @Field("address") String address, @Field("city") String city,
                                                        @Field("pincode") String pincode, @Field("state") String state,
                                                        @Field("country") String country, @Field("mobile") String mobile,
                                                        @Field("alternate_number") String alternate_number, @Field("whatsapp_number") String whatsapp_number,
                                                        @Field("birth_day") String birth_day, @Field("gender") String gender,
                                                        @Field("blood_group") String blood_group, @Field("marital_status") String marital_status,
                                                        @Field("marriage_date") String marriage_date, @Field("child_count") String child_count,
                                                        @Field("email_address") String email_address, @Field("is_head_of_family") String is_head_of_family);

    @FormUrlEncoded
    @POST("app/add_remove_address")
    Call<AddAddressResponse> addAddress(@Field("member_id") String memberId, @Field("app_token") String appToken,
                                        @Field("method") String method, @Field("address1") String address1,
                                        @Field("address2") String address2, @Field("post") String post,
                                        @Field("district") String district, @Field("city") String city,
                                        @Field("pincode") String pincode, @Field("state") String getState_name,
                                        @Field("country") String country, @Field("address_type") String address_type);

    @FormUrlEncoded
    @POST("app/add_update_promises")
    Call<UpdatePromiseResponse> updatePromises(@Field("member_id") String memberId, @Field("app_token") String appToken,
                                           @Field("method") String method, @Field("navkar_mantra") String navkar_mantra,
                                           @Field("swadhyay") String swadhyay, @Field("sant_darshan") String sant_darshan,
                                           @Field("samayik") String samayik, @Field("navkarsi") String navkarsi,
                                           @Field("pratikraman") String pratikraman, @Field("chovihar") String chovihar,
                                           @Field("others") String others, @Field("special") String special);
    @FormUrlEncoded
    @POST("app/add_update_knowledge")
    Call<UpdateKnowledgeResponse> updateKnowledge(@Field("member_id") String memberId, @Field("app_token") String appToken,
                                                 @Field("method") String method, @Field("navkar_mantra") String navkar_mantra,
                                                 @Field("samayik") String samayik, @Field("pratikraman") String pratikraman,
                                                 @Field("bol_thokde") String bol_thokde, @Field("shastra_gyan") String shastra_gyan,
                                                 @Field("vishesh_gyan") String vishesh_gyan);

    @FormUrlEncoded
    @POST("app/add_remove_achievement")
    Call<AddAchievementResponse> addAchievements(@Field("member_id") String memberId, @Field("app_token") String appToken,
                                                 @Field("method") String method, @Field("achievement_sector") String achievement_sector,
                                                 @Field("achievement_level") String achievement_level, @Field("achievement_type") String achievement_type,
                                                 @Field("achievement_detail") String achievement_detail, @Field("achievement_year") String achievement_year);

    @FormUrlEncoded
    @POST("app/add_remove_exams")
    Call<AddExamResponse> addExams(@Field("member_id") String memberId, @Field("app_token") String appToken,
                                          @Field("method") String method, @Field("exam_name") String exam_name,
                                          @Field("exam_institute_name") String exam_institute_name, @Field("exam_year") String exam_year);

    @FormUrlEncoded
    @POST("app/add_remove_education")
    Call<AddEducationResponse> addEducation(@Field("member_id") String memberId, @Field("app_token") String appToken,
                                            @Field("method") String method, @Field("education_name") String education_name,
                                            @Field("education_description") String education_description, @Field("education_score") String education_score,
                                            @Field("education_institute") String education_institute, @Field("education_year") String education_year);

    @FormUrlEncoded
    @POST("app/add_remove_business")
    Call<AddBusinessResponse> addBusiness(@Field("member_id") String memberId, @Field("app_token") String appToken,
                                          @Field("method") String method, @Field("business_type") String business_type,
                                          @Field("business_name") String business_name, @Field("business_role") String business_role,
                                          @Field("business_start_year") String business_start_year);

    @FormUrlEncoded
    @POST("app/add_remove_address")
    Call<DeleteAddressResponse> removeAddress(@Field("member_id") String memberId,
                                              @Field("app_token") String appToken,
                                              @Field("method") String method,
                                              @Field("address_id") String address_id);

    @FormUrlEncoded
    @POST("app/password_change")
    Call<PasswordChangeResponse> passwordChange(@Field("member_id") String memberId,
                                                @Field("app_token") String appToken,
                                                @Field("current_password") String currentPassword,
                                                @Field("new_password") String newPassword);

    @FormUrlEncoded
    @POST("app/password_recover")
    Call<PasswordRecoverResponse> passwordRecover(@Field("email_address") String emaiId,
                                                  @Field("mobile") String mobileNo,
                                                  @Field("first_name") String fName,
                                                  @Field("last_name") String c);

    @FormUrlEncoded
    @POST("app/add_remove_achievement")
    Call<DeleteAchievementResponse> deleteAchievement(@Field("member_id") String memberId, @Field("app_token") String appToken, @Field("method") String method, @Field("achievement_id") String achievement_id);

    @FormUrlEncoded
    @POST("app/add_remove_business")
    Call<DeleteBusinessResponse> deleteBusiness(@Field("member_id") String memberId, @Field("app_token") String appToken, @Field("method") String method, @Field("business_id") String achievement_id);


    @FormUrlEncoded
    @POST("app/add_remove_education")
    Call<DeleteEducationResponse> deleteEducation(@Field("member_id") String memberId, @Field("app_token") String appToken, @Field("method") String method, @Field("education_id") String education_id);

    @FormUrlEncoded
    @POST("app/add_remove_exams")
    Call<DeleteExamResponse> deleteExams(@Field("member_id") String memberId, @Field("app_token") String appToken, @Field("method") String method, @Field("exam_id") String exam_id);

    @FormUrlEncoded
    @POST("app/add_remove_role")
    Call<DeleteSocialRoleResponse> deleteSocialRole(@Field("member_id") String memberId, @Field("app_token") String appToken, @Field("method") String method, @Field("role_id") String role_id);


    @GET("api/states")
    Call<List<State>> getStateList();

    @GET("api/cities")
    Call<List<City>> getCityList();

    @FormUrlEncoded
    @POST("app/add_update_services")
    Call<UpdateServiceResponse> updateServices(@Field("member_id") String memberId, @Field("app_token") String appToken,
                                               @Field("method") String method, @Field("service_time") String service_time,
                                               @Field("service_money") String service_money , @Field("service_office") String service_office,
                                               @Field("service_experience") String service_experience, @Field("service_thoughts") String service_thoughts,
                                               @Field("service_others") String service_others);


    @FormUrlEncoded
    @POST("app/add_remove_role")
    Call<AddScocialRoleResponse> addSocilaRole(@Field("member_id") String memberId, @Field("app_token") String app_Token,
                                               @Field("method") String method ,@Field("start_date") String start_date,
                                               @Field("end_date") String end_date, @Field("social_org_name") String social_org_name,
                                               @Field("social_org_active") String social_org_active,@Field("social_org_role") String social_org_role,
                                               @Field("social_org_role_level") String social_org_role_level);

    @FormUrlEncoded
    @POST("app/add_update_hobbies")
    Call<UpdateHobbiesResponse> updateHobbies(@Field("member_id") String memberId, @Field("app_token") String appToken,
                                              @Field("method") String method, @Field("org_planning") String org_planning,
                                              @Field("org_comp_operator") String org_comp_operator , @Field("org_project_operation") String org_project_operation,
                                              @Field("org_fund_raise") String org_fund_raise, @Field("org_migration_prg") String org_migration_prg,
                                              @Field("org_oration_trainer") String org_oration_trainer, @Field("org_management_phones") String org_management_phones,
                                              @Field("rel_medical_service") String rel_medical_service, @Field("rel_vihar_service") String rel_vihar_service,
                                              @Field("rel_gochary_service") String rel_gochary_service, @Field("rel_jap_tap_cordination") String rel_jap_tap_cordination,@Field("rel_swadhyai_service")String rel_swadhyai_service,
                                              @Field("rel_kar_sewa") String rel_kar_sewa, @Field("rel_shivir_management") String rel_shivir_management,
                                              @Field("rel_writeup") String rel_writeup, @Field("rel_drawing") String rel_drawing,
                                              @Field("rel_self_learning") String rel_self_learning, @Field("rel_teaching") String rel_teaching,
                                              @Field("rel_branch") String rel_branch, @Field("social_human_service") String social_human_service,
                                              @Field("social_education_service") String social_education_service, @Field("social_medical_service") String social_medical_service,
                                              @Field("social_veg_publicity") String social_veg_publicity, @Field("social_lit_service") String social_lit_service,
                                              @Field("social_water_kiosk_service") String social_water_kiosk_service, @Field("social_web_handling") String social_web_handling,
                                              @Field("social_speech") String social_speech, @Field("social_drug_rehab") String social_drug_rehab);


    @FormUrlEncoded
    @POST("api/families")
    Call<FamiliesResponse> selectFamily(@Field("Local\tSangh\tID") String lacalSanghID, @Field("First\tName") String firstName,
                                        @Field("Middle\tName") String middleName , @Field("Last\tName") String lastName,
                                        @Field("City") String city);

    @FormUrlEncoded
    @POST("api/native_families")
    Call<NativeFamilyResponse> selectNativeFamily(@Field("Local\tSangh\tID") String lacalSanghID,
                                                  @Field("City") String city);

    @FormUrlEncoded
    @POST("api/family_members")
    Call<FamilyMembersResponse> familyMembers(@Field("member_id") String member_id,
                                              @Field("app_token") String app_token);



}
