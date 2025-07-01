package com.optimum.AvicaStaff.HttpUtils;


import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.optimum.AvicaStaff.Listener.ServiceListener;
import com.optimum.AvicaStaff.Models.AppointmentData;
import com.optimum.AvicaStaff.Models.Chat.ChatRoom;
import com.optimum.AvicaStaff.Models.DashboardData;
import com.optimum.AvicaStaff.Models.DoctorProfile.ProfileData;
import com.optimum.AvicaStaff.Models.Education;
import com.optimum.AvicaStaff.Models.Medication;
import com.optimum.AvicaStaff.Models.Notifications;
import com.optimum.AvicaStaff.Models.PatientList;
import com.optimum.AvicaStaff.Models.PatientProfile;
import com.optimum.AvicaStaff.Models.RAG;
import com.optimum.AvicaStaff.Models.Reports;
import com.optimum.AvicaStaff.Models.Tutorials;
import com.optimum.AvicaStaff.Models.User;
import com.optimum.AvicaStaff.Utils.UserPrefs;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class AppServices {

    public static void Login(String TAG, JSONObject userObject, final ServiceListener<User, String> listener) {
        RestAPI.PostJsonRequest(TAG, ConfigConstants.Login, userObject, new ServiceListener<JSONObject, VolleyError>() {
            @Override
            public void success(JSONObject success) {
                try {
                    if (success.getBoolean("success")) {
                        UserPrefs.getInstance().saveUser(success.getJSONObject("data"));
                        User user = GsonUtils.fromJSON(success.getJSONObject("data"), User.class);
                        listener.success(user);
                    } else listener.error(success.getJSONObject("data").getString("message"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(VolleyError error) {
                listener.error(error.getMessage());
            }
        });

    }

    public static void ForgetPassword(String TAG, JSONObject userObject, final ServiceListener<String, String> listener) {
        RestAPI.PostJsonRequest(TAG, ConfigConstants.ForgetPassword, userObject, new ServiceListener<JSONObject, VolleyError>() {
            @Override
            public void success(JSONObject success) {
                try {
                    if (success.getBoolean("success")) {
                        listener.success(success.getJSONObject("data").getString("message"));
                    } else listener.error(success.getJSONObject("data").getString("message"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(VolleyError error) {
                listener.error(error.getMessage());
            }
        });

    }


    public static void TechnicalSupport(String TAG, JSONObject userObject, final ServiceListener<String, String> listener) {
        RestAPI.PostJsonRequest(TAG, ConfigConstants.TechnicalSupport, userObject, new ServiceListener<JSONObject, VolleyError>() {
            @Override
            public void success(JSONObject success) {
                try {
                    if (success.getBoolean("success")) {
                        listener.success(success.getJSONObject("data").toString());
                    } else listener.error(success.getJSONObject("data").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(VolleyError error) {
                listener.error(error.getMessage());
            }
        });

    }

    public static void ResetPass(String TAG, JSONObject userObject, final ServiceListener<String, String> listener) {
        RestAPI.PostJsonRequest(TAG, ConfigConstants.ResetPass, userObject, new ServiceListener<JSONObject, VolleyError>() {
            @Override
            public void success(JSONObject success) {
                try {
                    if (success.getBoolean("success")) {
                        listener.success(success.getJSONObject("data").toString());
                    } else listener.error(success.getJSONObject("data").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(VolleyError error) {
                listener.error(error.getMessage());
            }
        });

    }

    public static void AddMeasurment(String TAG, String id, JSONObject userObject, final ServiceListener<String, String> listener) {
        RestAPI.PostJsonRequest(TAG, ConfigConstants.AddMeasurment + id, userObject, new ServiceListener<JSONObject, VolleyError>() {
            @Override
            public void success(JSONObject success) {
                try {
                    if (success.getBoolean("success")) {
                        listener.success(success.getJSONObject("data").toString());
                    } else listener.error(success.getJSONObject("data").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(VolleyError error) {
                listener.error(error.getMessage());
            }
        });

    }

    public static void patientProfile(String TAG, String id, final ServiceListener<PatientProfile, String> listener) {
        RestAPI.GetUrlEncodedRequest(TAG, ConfigConstants.patientprofile + id, new ServiceListener<JSONObject, VolleyError>() {
            @Override
            public void success(JSONObject success) {
                try {
                    PatientProfile patientProfile = GsonUtils.fromJSON(success.getJSONObject("data"), PatientProfile.class);
                    listener.success(patientProfile);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(VolleyError error) {
                listener.error(error.getMessage());
            }
        });
    }

    public static void DoctorProfile(String TAG, String id, final ServiceListener<ProfileData, String> listener) {
        RestAPI.GetUrlEncodedRequest(TAG, ConfigConstants.doctorprofile + id, new ServiceListener<JSONObject, VolleyError>() {
            @Override
            public void success(JSONObject success) {
                try {
                    ProfileData doctorProfile = GsonUtils.fromJSON(success.getJSONObject("data"), ProfileData.class);
                    listener.success(doctorProfile);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(VolleyError error) {
                listener.error(error.getMessage());
            }
        });
    }



    public static void Dashboard_doc(String TAG, final ServiceListener<DashboardData, String> listener) {
        RestAPI.GetUrlEncodedRequest(TAG, ConfigConstants.DocDashboard, new ServiceListener<JSONObject, VolleyError>() {
            @Override
            public void success(JSONObject success) {
                try {
                    DashboardData dashboardData = GsonUtils.fromJSON(success.getJSONObject("data"), DashboardData.class);
                    listener.success(dashboardData);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(VolleyError error) {
                listener.error(error.getMessage());
            }
        });
    }


    public static void getNotificiation(String TAG, final ServiceListener<ArrayList<Notifications>, String> listener) {
        RestAPI.GetUrlEncodedRequest(TAG, ConfigConstants.notifications, new ServiceListener<JSONObject, VolleyError>() {
            @Override
            public void success(JSONObject success) {
                try {
                    ArrayList<Notifications> data = new ArrayList<>(Arrays.asList(GsonUtils.fromJSON(success.getJSONArray("data").toString(), Notifications[].class)));
                    listener.success(data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(VolleyError error) {
                listener.error(error.getMessage());
            }
        });
    }

    public static void getEducation(String TAG, final ServiceListener<ArrayList<Education>, String> listener) {
        RestAPI.GetUrlEncodedRequest(TAG, ConfigConstants.Education, new ServiceListener<JSONObject, VolleyError>() {
            @Override
            public void success(JSONObject success) {
                try {
                    ArrayList<Education> data = new ArrayList<>(Arrays.asList(GsonUtils.fromJSON(success.getJSONArray("data").toString(), Education[].class)));
                    listener.success(data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(VolleyError error) {
                listener.error(error.getMessage());
            }
        });

    }
    public static void getMedication(String TAG,String id, final ServiceListener<ArrayList<Medication>, String> listener) {
        RestAPI.GetUrlEncodedRequest(TAG, ConfigConstants.Medication+id, new ServiceListener<JSONObject, VolleyError>() {
            @Override
            public void success(JSONObject success) {
                try {
                    ArrayList<Medication> data = new ArrayList<>(Arrays.asList(GsonUtils.fromJSON(success.getJSONArray("data").toString(), Medication[].class)));
                    listener.success(data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(VolleyError error) {
                listener.error(error.getMessage());
            }
        });

    }
    public static void getReports(String TAG,String id, final ServiceListener<ArrayList<Reports>, String> listener) {
        RestAPI.GetUrlEncodedRequest(TAG, ConfigConstants.Report+"?patient_id="+id, new ServiceListener<JSONObject, VolleyError>() {
            @Override
            public void success(JSONObject success) {
                try {
                    ArrayList<Reports> data = new ArrayList<>(Arrays.asList(GsonUtils.fromJSON(success.getJSONArray("data").toString(), Reports[].class)));
                    listener.success(data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(VolleyError error) {
                listener.error(error.getMessage());
            }
        });

    }
    public static void getPatientList(String TAG, final ServiceListener<ArrayList<PatientList>, String> listener) {
        RestAPI.GetUrlEncodedRequest(TAG, ConfigConstants.getPatientList, new ServiceListener<JSONObject, VolleyError>() {
            @Override
            public void success(JSONObject success) {
                try {
                    ArrayList<PatientList> data = new ArrayList<>(Arrays.asList(GsonUtils.fromJSON(success.getJSONArray("data").toString(), PatientList[].class)));
                    listener.success(data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(VolleyError error) {
                listener.error(error.getMessage());
            }
        });

    }
    public static void getRAG(String TAG,String type, final ServiceListener<ArrayList<RAG>, String> listener) {
        RestAPI.GetUrlEncodedRequest(TAG, ConfigConstants.getRAG+type, new ServiceListener<JSONObject, VolleyError>() {
            @Override
            public void success(JSONObject success) {
                try {
                    ArrayList<RAG> data = new ArrayList<>(Arrays.asList(GsonUtils.fromJSON(success.getJSONArray("data").toString(), RAG[].class)));
                    listener.success(data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(VolleyError error) {
                listener.error(error.getMessage());
            }
        });

    }

    public static void gettelemedappointments(String TAG,  final ServiceListener<AppointmentData, String> listener) {
        RestAPI.GetUrlEncodedRequest(TAG, ConfigConstants.gettelemedappointments , new ServiceListener<JSONObject, VolleyError>() {
            @Override
            public void success(JSONObject success) {
                try {
                    AppointmentData dashboardData = GsonUtils.fromJSON(success.getJSONObject("data"), AppointmentData.class);
                    listener.success(dashboardData);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(VolleyError error) {
                listener.error(error.getMessage());
            }
        });
    }

    public static void createOrSelectChatRoom(String TAG, JSONObject userObject, final ServiceListener<ChatRoom, String> listener) {
        RestAPI.PostJsonRequest(TAG, ConfigConstants.createOrSelectChatRoom, userObject, new ServiceListener<JSONObject, VolleyError>() {
            @Override
            public void success(JSONObject success) {
                try {
                    if (success.getBoolean("success")) {
                        ChatRoom user = GsonUtils.fromJSON(success.getJSONObject("data"), ChatRoom.class);
                        listener.success(user);
                    } else listener.error(success.getJSONObject("data").getString("message"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(VolleyError error) {
                listener.error(error.getMessage());
            }
        });

    }


    public static void UploadFileRequest(String TAG, String apiEndpoint, File file,
                                         final ServiceListener<NetworkResponse, VolleyError> listener) {

        MultipartRequest multipartRequest = new MultipartRequest(
                ConfigConstants.API_BASE_URL + apiEndpoint,
                file,
                "file", // <- This must match server-side form field key
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        listener.success(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.error(error);
                    }
                }
        );

        HttpRequestHandler.getInstance().addToRequestQueue(multipartRequest, TAG);
    }

    public static void Createeducation(String TAG, JSONObject userObject, final ServiceListener<String, String> listener) {
        RestAPI.PostJsonRequest(TAG, ConfigConstants.Createeducation, userObject, new ServiceListener<JSONObject, VolleyError>() {
            @Override
            public void success(JSONObject success) {
                try {
                    if (success.getBoolean("success")) {
                        listener.success(success.getJSONObject("data").toString());
                    } else listener.error(success.getJSONObject("data").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(VolleyError error) {
                listener.error(error.getMessage());
            }
        });

    }


    public static void getTutorials(String TAG,String category,  final ServiceListener<Tutorials, String> listener) {
        RestAPI.GetUrlEncodedRequest(TAG, ConfigConstants.getTutorials+"?category="+category, new ServiceListener<JSONObject, VolleyError>() {
            @Override
            public void success(JSONObject success) {
                try {
                    Tutorials dashboardData = GsonUtils.fromJSON(success.getJSONObject("data"), Tutorials.class);
                    listener.success(dashboardData);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(VolleyError error) {
                listener.error(error.getMessage());
            }
        });
    }


}