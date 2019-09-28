# IntroSlider
IntroSlider is a simple library which eliminates the need of separate IntroActivity by adding this feature to splash screen.


## Preview
<img src="https://media.giphy.com/media/YRVep22l3z0Wqddv2t/giphy.gif" width="240px" height="480px"/>

## Demo apk

https://drive.google.com/file/d/11CxGjhZ9OcXWLY3oLhcHOg-Q0IXfdVdr/view?usp=drivesdk

## Demo project link
https://github.com/mukulbhardwaj1313/IntroSliderDemo

## Usage

define arrays of String and drawable 

```

    int[] titles = new int[]{R.string.app_name, R.string.Page2Title, R.string.Page3Title, R.string.Page4Title, R.string.Page5Title,};
   
    int[] messages = new int[]{R.string.Page1Message, R.string.Page2Message, R.string.Page3Message, R.string.Page4Message, R.string.Page5Message,};

    int[] title_image_logo = new int[]{R.drawable.ic_analytics, R.drawable.ic_infinity_logo, R.drawable.ic_analytics, R.drawable.ic_lock_2, R.drawable.ic_infinity_logo};

    int[] bg_image = new int[]{R.drawable.welc1, R.drawable.welc2, R.drawable.welc3, R.drawable.welc1, R.drawable.welc3};

    int[] text_color = new int[]{R.color.colorDarkGray, R.color.colorWhite, R.color.colorDarkGray, R.color.colorDarkGray, R.color.colorDarkGray};
   
```

Define IntroSlider in splash screen 

```

        IntroSlider introSlider=findViewById(R.id.intro);

        introSlider.provideBackgroundImages(bg_image)   //array defined above
                .provideSubTitles(messages)           
                .provideTitleImages(title_image_logo)
                .provideTitles(titles)
                .provideTextColor(text_color)      //provideTextColor(int) method will have priority above provideTextColor(int[]) do not use this method along with below method
//                .provideTextColor(R.color.colorAccent)   // this method will set single colour for all titles and message

                .setDelayDuration(900)         //default 300
                .setAnimationDuration(900)     //default 900
                .setTranslationUpwards(400)     //default 500
                .setTitleInCenter(true)         //default false. title will be at bottom
                .setBackGroundImageScaleType(ImageView.ScaleType.CENTER_CROP)   //default FIT_XY
                .setbottomDotsRadiusInDP(15)     //default 8dp
                .showbottomDots(true);            //default true        

      



```

to check current page

```

  introSlider.setOnSlideChangeListner(new OnSlideChangeListner() {
            @Override
            public void onPageChanged(int page) {
                Log.d(TAG, "onPageChanged: "+page );
            }
        });
```

call below method to start slides and animation
e.g. if you want to check whether a user visited first time or not
```
                if(firstTimeUser){
                introSlider.initiateAnimation();            // initiateAnimation method must be called after provideTitles
                }else{
                // go to login/main ancivity
                }
```



## From XML

```
    <com.mukulbhardwaj1313.introslider.IntroSlider
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/intro"

        app:delay_duration="900"
        app:animation_duration="900"
        app:title_image_translation="400"
        app:backGround_image_scaleType="centerCrop"
        app:bottom_dot_radius="5dp"
        app:show_bottom_dots="true"
        app:title_in_center="true"
        app:background_images="@array/random_imgs"
        app:title_images="@array/title_images"
        app:titles="@array/titles"
        app:sub_titles="@array/messages"
        />
```
Create following arrays in arrays.xml

```

<?xml version="1.0" encoding="utf-8"?>
<resources>

    <integer-array name="random_imgs">
        <item>@drawable/welc1</item>
        <item>@drawable/welc2</item>
        <item>@drawable/welc3</item>
        <item>@drawable/welc1</item>
        <item>@drawable/welc3</item>
    </integer-array>

    <integer-array name="title_images">
        <item>@drawable/ic_analytics</item>
        <item>@drawable/ic_infinity_logo</item>
        <item>@drawable/ic_analytics</item>
        <item>@drawable/ic_lock_2</item>
        <item>@drawable/intro5</item>
    </integer-array>

    <integer-array name="titles">
        <item>@string/app_name</item>
        <item>@string/Page2Title</item>
        <item>@string/Page3Title</item>
        <item>@string/Page4Title</item>
        <item>@string/Page5Title</item>

    </integer-array>

    <integer-array name="messages">
        <item>@string/Page1Message</item>
        <item>@string/Page2Message</item>
        <item>@string/Page3Message</item>
        <item>@string/Page4Message</item>
        <item>@string/Page5Message</item>

    </integer-array>
</resources>
```

## Gradle Dependency

Add JitPack repository to your root `build.gradle` file
```
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
Add the dependency to your app `build.gradle` file
```
dependencies {
       implementation 'com.github.mukulbhardwaj1313:IntroSlider:0.1.41_beta'
}
```

Currently this library is in beta stage. feel free to provide any suggestions.

## Credits
- (<a href="https://www.freepik.com/free-vector/abstract-ink-splash-banner-different-colors_4724857.htm">Background Images created by starline - www.freepik.com</a>).
- <div>Infinity icon used in title images made by <a href="https://www.flaticon.com/authors/freepik" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/"             title="Flaticon">www.flaticon.com</a></div>
- <div>Money bag icon used in title images made by <a href="https://www.flaticon.com/authors/freepik" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/"             title="Flaticon">www.flaticon.com</a></div>
- <div>Lock icon in title images made by <a href="https://www.flaticon.com/authors/pixel-perfect" title="Pixel perfect">Pixel perfect</a> from <a href="https://www.flaticon.com/"             title="Flaticon">www.flaticon.com</a></div>
