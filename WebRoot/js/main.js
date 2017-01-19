(function($, window, document, undefined) {
	var $window = $(window);
	$.fn.lazyload = function(options) {
		var elements = this;
		var $container;
		var settings = {
			threshold: 0,
			failure_limit: 0,
			event: "scroll",
			effect: "show",
			container: window,
			data_attribute: "original",
			skip_invisible: true,
			appear: null,
			load: null,
			placeholder: "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAANSURBVBhXYzh8+PB/AAffA0nNPuCLAAAAAElFTkSuQmCC"
		};

		function update() {
			var counter = 0;
			elements.each(function() {
				var $this = $(this);
				if (settings.skip_invisible && !$this.is(":visible")) {
					return
				}
				if ($.abovethetop(this, settings) || $.leftofbegin(this, settings)) {} else {
					if (!$.belowthefold(this, settings) && !$.rightoffold(this, settings)) {
						$this.trigger("appear");
						counter = 0
					} else {
						if (++counter > settings.failure_limit) {
							return false
						}
					}
				}
			})
		}
		if (options) {
			if (undefined !== options.failurelimit) {
				options.failure_limit = options.failurelimit;
				delete options.failurelimit
			}
			if (undefined !== options.effectspeed) {
				options.effect_speed = options.effectspeed;
				delete options.effectspeed
			}
			$.extend(settings, options)
		}
		$container = (settings.container === undefined || settings.container === window) ? $window : $(settings.container);
		if (0 === settings.event.indexOf("scroll")) {
			$container.bind(settings.event, function() {
				return update()
			})
		}
		this.each(function() {
			var self = this;
			var $self = $(self);
			self.loaded = false;
			if ($self.attr("src") === undefined || $self.attr("src") === false) {
				if ($self.is("img")) {
					$self.attr("src", settings.placeholder)
				}
			}
			$self.one("appear", function() {
				if (!this.loaded) {
					if (settings.appear) {
						var elements_left = elements.length;
						settings.appear.call(self, elements_left, settings)
					}
					$("<img />").bind("load", function() {
						var original = $self.attr("data-" + settings.data_attribute);
						$self.hide();
						if ($self.is("img")) {
							$self.attr("src", original)
						} else {
							$self.css("background-image", "url('" + original + "')")
						}
						$self[settings.effect](settings.effect_speed);
						self.loaded = true;
						var temp = $.grep(elements, function(element) {
							return !element.loaded
						});
						elements = $(temp);
						if (settings.load) {
							var elements_left = elements.length;
							settings.load.call(self, elements_left, settings)
						}
					}).attr("src", $self.attr("data-" + settings.data_attribute))
				}
			});
			if (0 !== settings.event.indexOf("scroll")) {
				$self.bind(settings.event, function() {
					if (!self.loaded) {
						$self.trigger("appear")
					}
				})
			}
		});
		$window.bind("resize", function() {
			update()
		});
		if ((/(?:iphone|ipod|ipad).*os 5/gi).test(navigator.appVersion)) {
			$window.bind("pageshow", function(event) {
				if (event.originalEvent && event.originalEvent.persisted) {
					elements.each(function() {
						$(this).trigger("appear")
					})
				}
			})
		}
		$(document).ready(function() {
			update()
		});
		return this
	};
	$.belowthefold = function(element, settings) {
		var fold;
		if (settings.container === undefined || settings.container === window) {
			fold = (window.innerHeight ? window.innerHeight : $window.height()) + $window.scrollTop()
		} else {
			fold = $(settings.container).offset().top + $(settings.container).height()
		}
		return fold <= $(element).offset().top - settings.threshold
	};
	$.rightoffold = function(element, settings) {
		var fold;
		if (settings.container === undefined || settings.container === window) {
			fold = $window.width() + $window.scrollLeft()
		} else {
			fold = $(settings.container).offset().left + $(settings.container).width()
		}
		return fold <= $(element).offset().left - settings.threshold
	};
	$.abovethetop = function(element, settings) {
		var fold;
		if (settings.container === undefined || settings.container === window) {
			fold = $window.scrollTop()
		} else {
			fold = $(settings.container).offset().top
		}
		return fold >= $(element).offset().top + settings.threshold + $(element).height()
	};
	$.leftofbegin = function(element, settings) {
		var fold;
		if (settings.container === undefined || settings.container === window) {
			fold = $window.scrollLeft()
		} else {
			fold = $(settings.container).offset().left
		}
		return fold >= $(element).offset().left + settings.threshold + $(element).width()
	};
	$.inviewport = function(element, settings) {
		return !$.rightoffold(element, settings) && !$.leftofbegin(element, settings) && !$.belowthefold(element, settings) && !$.abovethetop(element, settings)
	};
	$.extend($.expr[":"], {
		"below-the-fold": function(a) {
			return $.belowthefold(a, {
				threshold: 0
			})
		},
		"above-the-top": function(a) {
			return !$.belowthefold(a, {
				threshold: 0
			})
		},
		"right-of-screen": function(a) {
			return $.rightoffold(a, {
				threshold: 0
			})
		},
		"left-of-screen": function(a) {
			return !$.rightoffold(a, {
				threshold: 0
			})
		},
		"in-viewport": function(a) {
			return $.inviewport(a, {
				threshold: 0
			})
		},
		"above-the-fold": function(a) {
			return !$.belowthefold(a, {
				threshold: 0
			})
		},
		"right-of-fold": function(a) {
			return $.rightoffold(a, {
				threshold: 0
			})
		},
		"left-of-fold": function(a) {
			return !$.rightoffold(a, {
				threshold: 0
			})
		}
	})
})(jQuery, window, document);






(function() {
	$(document).ready(function(e) {
		function audio_switch() {
			void 0 != $("#music_audio") && (audio_switch_ikea ? ($("#music_audio")[0].pause(), audio_switch_ikea = !1, $("#music_audio")[0].currentTime = 0, ikea_au.find("span").eq(0).css("display", "none"), ikea_au.find("span").eq(1).css("display", "inline-block")) : (audio_switch_ikea = !0, $("#music_audio")[0].play(), ikea_au.find("span").eq(1).css("display", "none"), ikea_au.find("span").eq(0).css("display", "inline-block")))
		}

		$("#loading").hide(), $("body").append('<div id="ikea-audio" class="ikea-audio abs"><div class="music"><p class="music_audio"><span class="abs audio_open"></span><span class="abs audio_close"></span></p><audio id="music_audio" loop="loop" preload="preload"><source src="images/music2.mp3" type="audio/mpeg">\u60a8\u7684\u6d4f\u89c8\u5668\u4e0d\u652f\u6301HTML5\u97f3\u9891\u683c\u5f0f</audio></div></div>');
		var _height = $(window).height(),
			_width = $(window).width();
			_swiperslide = $(".swiper-slide"), $("#device div").css({
			height: _height,
			width: _width
		}), $("#device img").css({
			height: _height,
			width: _width
		}), $("#device").css({
			height: _height,
			width: _width
		}), $("div.videocontroller, div.video").css({
			height: "42%",
			width: "80%",
			"margin-left": "10%",
			bottom: "5%",
			left: 0,
			top: "auto"
		}), $("#ikea-audio").css({
			height: .0496 * _height,
			width: .0781 * _width
		}), $("#ikea-audio .audio_open").css({
			"background-position": .0781 * -_width
		});
		var yourtime = "2014-03-28";
		yourtime = yourtime.replace(/-/g, "/");
		var nowday = new Date,
			tgday = new Date(Date.parse(yourtime));
		nowday > tgday && ($("#cityMain").attr({
			"data-original": "images/ikea_city_1.jpg"
		}), $("#cityMark").attr({
			"data-original": "images/ikea_city_1.png"
		}));
		var ikea_au = $(".ikea-audio").find(".music"),
			audio_switch_ikea = !0;
		ikea_au.on("click", audio_switch);
		var _videostart = !1;
		$("#videocontroller").on("click", function() {
			audio_switch_ikea && ikea_au.trigger("click"), _videostart = !0, $("#video").delay(800).show().find("video")[0].play()
		}), _swiperslide.find("img.lazy").lazyload({
			threshold: _height,
			skip_invisible: !1
		});
		
		
		var mySwiper = new Swiper("#swiper-container", {
			pagination: "",
			loop: !1,
			mode: "vertical",
			grabCursor: !0,
			paginationClickable: !1,
			onSlideChangeStart: function(a) {
				1 == a.activeIndex && $("#music_audio").length > 0 && audio_switch_ikea && $("#music_audio")[0].play(), a.activeIndex < 5 ? $("img.lazy").lazyload({
					threshold: 2 * _height,
					skip_invisible: !1
				}) : $("img.lazy").lazyload({
					threshold: 3 * _height,
					skip_invisible: !1
				}), _videostart && $("#video").delay(800).hide().find("video")[0].pause()
			},
			onSlideChangeEnd: function(a) {
				var b = a.activeSlide();
				$(b).find("img").removeClass("lazy"), $(b).find(".draw").animate({
					opacity: 1
				}, 1e3), $(b).find(".info").delay(800).animate({
					opacity: 1,
					left: 0
				}, {
					duration: 1200
				})
			}
		});
		
		
	})
})();