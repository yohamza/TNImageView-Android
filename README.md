# TNImageView-Android
Android Library for making scale-able and rotatable image views or giving this power to your own image view




![Independent they are :p](https://media.giphy.com/media/3oFzmelzRf8Cou0BJC/giphy.gif) ------
![Independent they are :p](https://media.giphy.com/media/l1IBijSn8Fz1XzAUE/giphy.gif)


# Installing

For a working implementation of this project see the `app/` folder.

1. Add it in your root 'build.gradle' at the end of repositories:

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```



2. Include the following dependency.

```
compile 'com.github.AmeerHamzaaa:TNImageView-Android:0.1.2'
```

# Usage

3. In your `onCreate` method (or `onCreateView` for a fragment), initialize the TNImageView object:

```java
// Initialize the TNImageView object
TNImageView tnImage = new TNImageView():

// pass your ImageView which you want to make rotatable and scaleable
tnImage.makeRotatableScalable(imageview);

//you can also select if the touched view comes to front or not
tnImage.bringToFrontOnTouch(true);
```

# Remember

Put all your imageviews inside a relativeLayout they will not work with any other layout. We are working on it.

### That's all you need to do, but if you want to add a list of ImageViews, then use this function

```java
// add your imageviews to a list and than pass the list to the object it will make all of them rotatable and scalable.
tnImage.addListofImageViews(imageViews);
```


Please open an issue if you find any are missing.

# Developed By

 * Ameer Hamza - <aha1475@gmail.com>

# Contributions

 * Please, read the README file before opening an issue, thanks.
 * Please, all the Pull Request must be sent to the dev branch, thanks..
 
 ## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
