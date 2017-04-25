//
//  JDGAnnotaionView.m
//  RCTBaiduMap
//
//  Created by JDG on 2017/4/17.
//  Copyright © 2017年 lovebing.org. All rights reserved.
//

#import "JDGAnnotationView.h"

@implementation JDGAnnotationView{
    UIImageView *_imageView;
    UILabel *_titleLabel;
    UILabel *_subtitleLabel;
}

- (id)initWithAnnotation:(id<BMKAnnotation>)annotation reuseIdentifier:(NSString *)reuseIdentifier {
    self = [super initWithAnnotation:annotation reuseIdentifier:reuseIdentifier];
    if (self) {
        _imageView = [[UIImageView alloc] init];
        [self addSubview:_imageView];
        
        _titleLabel = [[UILabel alloc] init];
        [self addSubview:_titleLabel];
        
        _subtitleLabel = [[UILabel alloc] init];
        [self addSubview:_subtitleLabel];
        
        if ([annotation isKindOfClass:[JDGAnnotation class]]) {
            [self customizedWithAnnotation:annotation];
        }
    }
    return self;
}

- (void)prepareForReuse {
    [super prepareForReuse];
    _titleLabel.text = nil;
    _subtitleLabel.text = nil;
    _imageView.image = nil;
    _imageView.transform = CGAffineTransformIdentity;
}

- (void)customizedWithAnnotation:(JDGAnnotation *)anno {
    self.annotation = anno;
    [self setFrontTitle:anno.frontTitle];
    [self setFrontSubtitle:anno.frontSubtitle];
    [self setBackgroundHeading:anno.backgroundImageHeading];
    [self setBackgroundImage:anno.backgroundImage];
    [self setBackgroundAnimateImages:anno.animateBackgroundImages];
    [self setBackgroundAnimateDuration:anno.animateBackgroundDuration];
    [self setBackgroundAnimating:anno.isBackgroundAnimating];
}

- (void)setFrontTitle:(NSString *)title {
    _titleLabel.text = title;
}

- (void)setFrontSubtitle:(NSString *)subtitle {
    _subtitleLabel.text = subtitle;
}

- (void)setBackgroundHeading:(CLLocationDegrees)heading {
    CLLocationDegrees r = heading/180*M_PI;
    _imageView.transform = CGAffineTransformRotate(CGAffineTransformIdentity, r);
}

- (void)setBackgroundImage:(UIImage *)image {
    _imageView.image = image;
    [_imageView sizeToFit];
    self.bounds = _imageView.bounds;
}

- (void)setBackgroundAnimateImages:(NSArray<UIImage *> *)animateBackgroundImages {
    _imageView.animationImages = animateBackgroundImages;
}

- (void)setBackgroundAnimateDuration:(NSTimeInterval)duration {
    _imageView.animationDuration = duration;
}

- (void)setBackgroundAnimating:(BOOL)isAnimating {
    if (_imageView.isAnimating == isAnimating) { return; }
    if (isAnimating) {
        [_imageView startAnimating];
    } else {
        [_imageView stopAnimating];
    }
}

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

@end
