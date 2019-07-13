package rocks.topit.www.topit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import static android.content.Context.MODE_PRIVATE;

public class BackgroundWorker extends AsyncTask<String, Void, String>{
    Context context;
    AlertDialog alertDialog;
    SharedPreferences sp;
    int displayAlert = 0;

    BackgroundWorker (Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "https://www.topit.rocks/login/appLogin.php";
        String register_url = "https://www.topit.rocks/login/addAppNewUser.php";
        String recover_url = "https://www.topit.rocks/login/resetPassword.php";
        String ver_resend_url = "https://www.topit.rocks/login/sendVerification.php";
        String update_scores_url = "https://www.topit.rocks/leaderboards/update_ui_scores.php";


        if(type.equals("login")){
            try {
                String user_name = params[1];
                String password = params[2];
                String fromActivity = params[3];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name", "UTF-8")+"="+URLEncoder.encode(user_name, "UTF-8")+"&"+URLEncoder.encode("password")+"="+URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while((line = bufferedReader.readLine())!= null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                String resultCopy = result;

                String[] results = resultCopy.split(",");

                if(results[0].equals(user_name)){
                    if(results[1].equals("1")) {

                        // code for storing username to keep user logged in
                        sp = context.getSharedPreferences("login",MODE_PRIVATE);
                        sp.edit().putString("username", user_name).apply();
                        sp.edit().putBoolean("logged",true).apply();

                        Intent openMainScreen = new Intent(context, MainActivity.class);
                        openMainScreen.putExtra("user", result);
                        context.startActivity(openMainScreen);


                    }else{
                        if(fromActivity.equals("main")) {
                            Intent verifyEmail = new Intent(context, VerifyEmail.class);
                            verifyEmail.putExtra("username", user_name);
                            verifyEmail.putExtra("password", password);
                            context.startActivity(verifyEmail);
                            displayAlert = 1;
                            result = "Please verify your email. Please Allow 5 to 10 minutes to receive the verification code.";
                            return result;
                        }else{
                            result = "Please verify your email. Please Allow 5 to 10 minutes to receive the verification code.";
                            displayAlert = 1;
                            return result;
                        }
                    }
                }else if(results[0].equals("Bad Password")){
                    displayAlert = 1;
                    result = "The password is incorrect.";
                    return result;
                }else{
                    result = "That username does not exist.";
                    displayAlert = 1;
                    return result;
                }
                //return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(type.equals("register")){
            try {
                String userName = params[1];
                String firstName = params[2];
                String lastName = params[3];
                String password = params[4];
                String email = params[5];
                String birthdate = params[6];
                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data =
                        URLEncoder.encode("userName", "UTF-8")+"="+URLEncoder.encode(userName, "UTF-8")+"&"+
                        URLEncoder.encode("firstName", "UTF-8")+"="+URLEncoder.encode(firstName, "UTF-8")+"&"+
                        URLEncoder.encode("lastName", "UTF-8")+"="+URLEncoder.encode(lastName, "UTF-8")+"&"+
                        URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8")+"&"+
                        URLEncoder.encode("email", "UTF-8")+"="+URLEncoder.encode(email, "UTF-8")+"&"+
                        URLEncoder.encode("birthdate", "UTF-8")+"="+URLEncoder.encode(birthdate, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while((line = bufferedReader.readLine())!= null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                if(result.equals("bad username")) {
                    displayAlert = 1;
                    result = "Sorry that username is already in use. Please pick a new username.";
                    return result;
                }else if(result.equals("bad email")){
                    displayAlert = 1;
                    result = "Sorry there is already an account associated with that Email.\n\nTo retrieve your account info press the Retrieve Username and Password button on the log in screen.";
                    return result;
                }else if(result.equals("good")){
                    displayAlert = 1;
                    result = "account_Created";
                    Intent verifyEmail = new Intent(context, VerifyEmail.class);
                    verifyEmail.putExtra("username", userName);
                    verifyEmail.putExtra("password", password);
                    context.startActivity(verifyEmail);
                    return result;
                }else{
                    displayAlert = 1;
                    result = "There was an error registering your account.";
                    return result;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(type.equals("recover")) {
            try {
                String loginName = params[1];

                URL url = new URL(recover_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data =
                        URLEncoder.encode("loginName", "UTF-8") + "=" + URLEncoder.encode(loginName, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                displayAlert = 1;
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(type.equals("ver_resend")) {
            try {
                String username = params[1];
                //String password = params[2];

                URL url = new URL(ver_resend_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data =
                        URLEncoder.encode("userName", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }

                String[] results = result.split(",");
                if(results[0].equals("good")){
                    displayAlert = 1;
                    result = "A new verification code has been sent to: "+ results[1];
                }else{
                    displayAlert = 1;
                    result = "There was an error in sending a new verification code. the result was: " + result + "and the username is: " + username;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                displayAlert = 1;
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(type.equals("logged")) {
            try {
                String username = params[1];
                //String password = params[2];

                URL url = new URL(update_scores_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data =
                        URLEncoder.encode("userName", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }

                Intent openMainScreen = new Intent(context, MainActivity.class);
                openMainScreen.putExtra("user", result);
                context.startActivity(openMainScreen);

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                //return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    @Override
    protected void onPreExecute() {
        if (displayAlert == 1) {
            alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Log In Status");
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (displayAlert == 1) {
            alertDialog.setMessage(result);
            alertDialog.show();
        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
