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
    UIImageView *_frontImageView;
    UILabel *_titleLabel;
    UILabel *_subtitleLabel;
}

- (id)initWithAnnotation:(id<BMKAnnotation>)annotation reuseIdentifier:(NSString *)reuseIdentifier {
    self = [super initWithAnnotation:annotation reuseIdentifier:reuseIdentifier];
    if (self) {
        _imageView = [[UIImageView alloc] init];
        [self addSubview:_imageView];
        
        _frontImageView = [[UIImageView alloc] init];
        [self addSubview:_frontImageView];
        NSLayoutConstraint *centerX = [NSLayoutConstraint constraintWithItem:_frontImageView attribute:NSLayoutAttributeCenterX relatedBy:NSLayoutRelationEqual toItem:self attribute:NSLayoutAttributeCenterX multiplier:1 constant:-1];
        NSLayoutConstraint *centerY = [NSLayoutConstraint constraintWithItem:_frontImageView attribute:NSLayoutAttributeCenterY relatedBy:NSLayoutRelationEqual toItem:self attribute:NSLayoutAttributeCenterY multiplier:1 constant:-5];
        _frontImageView.translatesAutoresizingMaskIntoConstraints = NO;
        [self addConstraints:@[centerX, centerY]];
        
        _titleLabel = [[UILabel alloc] init];
        _titleLabel.textColor = [UIColor whiteColor];
        _titleLabel.font = [UIFont systemFontOfSize:14];
        [self addSubview:_titleLabel];
        
        centerX = [NSLayoutConstraint constraintWithItem:_titleLabel attribute:NSLayoutAttributeCenterX relatedBy:NSLayoutRelationEqual toItem:self attribute:NSLayoutAttributeCenterX multiplier:1 constant:-2];
        centerY = [NSLayoutConstraint constraintWithItem:_titleLabel attribute:NSLayoutAttributeCenterY relatedBy:NSLayoutRelationEqual toItem:self attribute:NSLayoutAttributeCenterY multiplier:1 constant:-5];
        _titleLabel.translatesAutoresizingMaskIntoConstraints = NO;
        [self addConstraints:@[centerX, centerY]];
        
        _subtitleLabel = [[UILabel alloc] init];
        _subtitleLabel.textColor = [UIColor whiteColor];
        _subtitleLabel.font = [UIFont systemFontOfSize:10];
        [self addSubview:_subtitleLabel];
        
        centerX = [NSLayoutConstraint constraintWithItem:_subtitleLabel attribute:NSLayoutAttributeLeft relatedBy:NSLayoutRelationEqual toItem:_titleLabel attribute:NSLayoutAttributeRight multiplier:1 constant:0];
        centerY = [NSLayoutConstraint constraintWithItem:_subtitleLabel attribute:NSLayoutAttributeBottom relatedBy:NSLayoutRelationEqual toItem:_titleLabel attribute:NSLayoutAttributeBottom multiplier:1 constant:0];
        _subtitleLabel.translatesAutoresizingMaskIntoConstraints = NO;
         [self addConstraints:@[centerX, centerY]];
        
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
    _frontImageView.image = nil;
    _imageView.transform = CGAffineTransformIdentity;
}

- (void)customizedWithAnnotation:(JDGAnnotation *)anno {
    self.annotation = anno;
    [self setFrontTitle:anno.frontTitle];
    [self setFrontSubtitle:anno.frontSubtitle];
    [self setFrontImage:anno.frontImage];
    [self setBackgroundHeading:anno.backgroundImageHeading];
    [self setBackgroundImage:anno.backgroundImage];
    [self setBackgroundAnimateImages:anno.animateBackgroundImages];
    [self setBackgroundAnimateDuration:anno.animateBackgroundDuration];
    [self setBackgroundAnimating:anno.isBackgroundAnimating];
    
    [self resetSubviewsLayout];
}

- (void)resetSubviewsLayout {
    self.frame = _imageView.bounds;
}

- (void)setFrontTitle:(NSString *)title {
    if (title.length > 0 && ![title isEqualToString:@" "]) {
        _titleLabel.text = title;
        _titleLabel.hidden = NO;
    } else {
        _titleLabel.text = @"测";
        _titleLabel.hidden = YES;
    }
    [_titleLabel sizeToFit];
}

- (void)setFrontSubtitle:(NSString *)subtitle {
    _subtitleLabel.text = subtitle;
    [_subtitleLabel sizeToFit];
}

- (void)setFrontImage:(UIImage *)image {
    if (image != nil) {
        _frontImageView.image = image;
        [_frontImageView sizeToFit];
        CGRect frame = _frontImageView.frame;
        frame.origin = CGPointMake(2, 5);
        _frontImageView.frame = frame;
        _frontImageView.hidden = NO;
    } else {
        _frontImageView.hidden = YES;
    }
}

- (void)setBackgroundHeading:(CLLocationDegrees)heading {
    CLLocationDegrees r = heading/180*M_PI;
    _imageView.transform = CGAffineTransformRotate(CGAffineTransformIdentity, r);
}

- (void)setBackgroundImage:(UIImage *)image {
    _imageView.image = image;
    [_imageView sizeToFit];
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
